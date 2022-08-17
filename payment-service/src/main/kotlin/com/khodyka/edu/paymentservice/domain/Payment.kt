package com.khodyka.edu.paymentservice.domain

import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

enum class PaymentStatus {
    NEW,
    SUCCESS,
    FAILED
}

class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,
    var userId: UUID,

    @Column(name = "from_account")
    var fromAccount: UUID,

    @Column(name = "to_account")
    var toAccount: UUID,
    var amount: BigDecimal,

    @Column(name = "payment_status")
    var paymentStatus: PaymentStatus = PaymentStatus.NEW
)
