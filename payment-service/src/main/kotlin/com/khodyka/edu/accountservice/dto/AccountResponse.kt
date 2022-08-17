package com.khodyka.edu.accountservice.dto

import java.math.BigDecimal
import java.util.*

class AccountResponse(
    val id: UUID,
    val balance: BigDecimal
)
