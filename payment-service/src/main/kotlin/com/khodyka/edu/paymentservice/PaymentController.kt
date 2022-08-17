package com.khodyka.edu.paymentservice

import com.khodyka.edu.paymentservice.dto.PaymentRequest
import com.khodyka.edu.paymentservice.dto.PaymentStatusRequest
import com.khodyka.edu.paymentservice.dto.UserPaymentsRequest
import com.khodyka.edu.paymentservice.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/payments")
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping
    fun createPayment(@RequestBody request: PaymentRequest) =
        paymentService.createPayment(request).let {
            ResponseEntity.ok(it)
        }

    @GetMapping("/{id}")
    fun getPaymentStatus(@PathVariable id: UUID) =
        paymentService.getPaymentStatus(PaymentStatusRequest(id)).let {
            ResponseEntity.ok(it)
        }

    @GetMapping
    fun getUserPayments(
        @RequestBody request: UserPaymentsRequest
//        @RequestParam("limit", required = false, defaultValue = "10") limit: Int,
//        @RequestParam("offset", required = false, defaultValue = "0") offset: Int,
    ) =
        paymentService.getUserPayments(request).let {
            ResponseEntity.ok(it)
        }

}
