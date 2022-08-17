package com.khodyka.edu.paymentservice.client

import com.khodyka.edu.accountservice.dto.ChangeAccountBalanceRequest
import com.khodyka.edu.paymentservice.dto.PaymentResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface AccountServiceClient {

    fun changeAccountBalance(
        @PathVariable id: UUID,
        @RequestBody request: ChangeAccountBalanceRequest
    ): PaymentResponse
}
