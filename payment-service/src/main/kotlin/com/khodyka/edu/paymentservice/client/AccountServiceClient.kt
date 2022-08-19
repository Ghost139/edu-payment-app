package com.khodyka.edu.paymentservice.client

import com.khodyka.edu.paymentservice.dto.PaymentResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.math.BigDecimal
import java.util.*

enum class BalanceOperation {
    INCREASE,
    DECREASE
}

class ChangeAccountBalanceRequest(
    var id: UUID? = null,
    val balanceOperation: BalanceOperation,
    val amount: BigDecimal
)

interface AccountServiceClient {

    fun changeAccountBalance(
        @PathVariable id: UUID,
        @RequestBody request: ChangeAccountBalanceRequest
    ): PaymentResponse
}
