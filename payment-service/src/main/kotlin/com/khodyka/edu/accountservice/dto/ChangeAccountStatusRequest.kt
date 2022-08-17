package com.khodyka.edu.accountservice.dto

import com.khodyka.edu.accountservice.domain.AccountStatus
import java.util.*

class ChangeAccountStatusRequest(
    var id: UUID? = null,
    val newStatus: AccountStatus
)
