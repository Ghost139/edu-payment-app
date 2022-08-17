package com.khodyka.edu.accountservice

import com.khodyka.edu.accountservice.dto.AccountRequest
import com.khodyka.edu.accountservice.dto.ChangeAccountBalanceRequest
import com.khodyka.edu.accountservice.dto.ChangeAccountStatusRequest
import com.khodyka.edu.accountservice.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/accounts")
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping
    fun openAccount(request: AccountRequest) =
        accountService.openAccount(request).let {
            ResponseEntity.ok(it)
        }

    @PatchMapping("/{id}/status")
    fun changeAccountStatus(
        @PathVariable id: UUID,
        @RequestBody request: ChangeAccountStatusRequest
    ) =
        request.let {
            it.id = id
        }.let {
            accountService.changeAccountStatus(request)
        }.run {
            ResponseEntity.ok()
        }

    @PatchMapping("/{id}/balance")
    fun changeAccountBalance(
        @PathVariable id: UUID,
        @RequestBody request: ChangeAccountBalanceRequest
    ) =
        request.let {
            it.id = id
        }.let {
            accountService.changeAccountBalance(request)
        }.run {
            ResponseEntity.ok()
        }
}
