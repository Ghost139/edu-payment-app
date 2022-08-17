package com.khodyka.edu.paymentservice.dto

class UserPaymentsResponse(
    val payments: List<PaymentResponse>,
    val totalElements: Long
)
