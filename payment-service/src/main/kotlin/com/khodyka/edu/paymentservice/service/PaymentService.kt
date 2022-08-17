package com.khodyka.edu.paymentservice.service

import com.khodyka.edu.paymentservice.domain.Payment
import com.khodyka.edu.paymentservice.dto.*
import com.khodyka.edu.paymentservice.repository.PaymentRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun createPayment(request: PaymentRequest) =
        request.let {
            Payment(
                userId = request.userId,
                fromAccount = it.fromAccount,
                toAccount = it.toAccount,
                amount = it.amount
            )
        }.let {
            paymentRepository.save(it)
        }.let {
            PaymentResponse(
                id = it.id!!,
                userId = it.userId,
                paymentStatus = it.paymentStatus
            )
        }

    fun getPaymentStatus(request: PaymentStatusRequest) =
        paymentRepository.findByIdOrNull(request.id)?.let {
            PaymentResponse(
                id = it.id!!,
                userId = it.userId,
                paymentStatus = it.paymentStatus
            )
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Payment ${request.id} not found")

    fun getUserPayments(request: UserPaymentsRequest) =
        paymentRepository.findByUserId(
            request.userId,
            PageRequest.of(request.limit, request.offset)
        ).let {
            UserPaymentsResponse(
                payments = it.content.map { payment ->
                    PaymentResponse(
                        id = payment.id!!,
                        userId = payment.userId,
                        paymentStatus = payment.paymentStatus
                    )
                },
                totalElements = it.totalElements
            )
        }

}
