package com.khodyka.edu.paymentservice.repository

import com.khodyka.edu.paymentservice.domain.Payment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentRepository : JpaRepository<Payment, UUID> {

    fun findByUserId(userId: UUID, pageRequest: Pageable): Page<Payment>
}
