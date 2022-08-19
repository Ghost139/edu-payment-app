package com.khodyka.edu.accountservice.domain

import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

enum class AccountStatus {
    ACTIVE,
    BLOCKED
}

enum class BalanceOperation {
    INCREASE,
    DECREASE
}

@Entity
//@Table(schema = "account_service", name = "account")
class Account (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "owner_id")
    var userId: UUID,
    var balance: BigDecimal = BigDecimal.ZERO,

    @Column(name = "account_status")
    var accountStatus: AccountStatus = AccountStatus.ACTIVE
)
