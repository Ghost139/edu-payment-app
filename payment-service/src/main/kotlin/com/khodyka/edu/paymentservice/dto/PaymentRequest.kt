package com.khodyka.edu.paymentservice.dto

import java.math.BigDecimal
import java.util.*

class PaymentRequest(
        val userId: UUID,
        val fromAccount: UUID,
        val toAccount: UUID,
        val amount: BigDecimal
)
