package com.khodyka.edu.accountservice.dto

import com.khodyka.edu.accountservice.domain.BalanceOperation
import java.math.BigDecimal
import java.util.*

class ChangeAccountBalanceRequest(
    var id: UUID? = null,
    val balanceOperation: BalanceOperation,
    val amount: BigDecimal
)
