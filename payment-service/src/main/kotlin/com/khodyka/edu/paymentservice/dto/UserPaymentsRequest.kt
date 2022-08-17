package com.khodyka.edu.paymentservice.dto

import java.util.*

class UserPaymentsRequest(
    val userId: UUID,
    val limit: Int = 10,
    val offset: Int = 0
)
