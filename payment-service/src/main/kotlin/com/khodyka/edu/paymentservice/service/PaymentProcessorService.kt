package com.khodyka.edu.paymentservice.service

import com.khodyka.edu.accountservice.domain.BalanceOperation
import com.khodyka.edu.accountservice.dto.ChangeAccountBalanceRequest
import com.khodyka.edu.paymentservice.client.AccountServiceClient
import com.khodyka.edu.paymentservice.domain.PaymentStatus
import com.khodyka.edu.paymentservice.repository.PaymentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class PaymentProcessorService(
    private val paymentRepository: PaymentRepository,
    private val accountServiceClient: AccountServiceClient
) {

    fun processPayment() {
        val pageRequest = PageRequest.of(0, DEFAULT_PAGE_SIZE)

        val paymentPage = paymentRepository.findAll(pageRequest)
        val totalPages = paymentPage.totalPages

        while (pageRequest.pageNumber != totalPages) {
            paymentPage.forEach { payment ->
                CompletableFuture.supplyAsync {
                    accountServiceClient.changeAccountBalance(
                        id = payment.fromAccount,
                        request = ChangeAccountBalanceRequest(
                            balanceOperation = BalanceOperation.DECREASE,
                            amount = payment.amount
                        )
                    )
                }.thenApply {
                    accountServiceClient.changeAccountBalance(
                        id = payment.toAccount,
                        request = ChangeAccountBalanceRequest(
                            balanceOperation = BalanceOperation.INCREASE,
                            amount = payment.amount
                        )
                    )
                }.handle { _, exception ->
                    if (exception != null) {
                        payment.paymentStatus = PaymentStatus.FAILED
                    }
                    payment.paymentStatus = PaymentStatus.SUCCESS
                }
            }
        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 3
    }
}
