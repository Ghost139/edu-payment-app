package com.khodyka.edu.paymentservice.dto

import com.khodyka.edu.paymentservice.domain.PaymentStatus
import java.util.*

class PaymentResponse(
        val id: UUID,
        val userId: UUID,
        val paymentStatus: PaymentStatus
)
