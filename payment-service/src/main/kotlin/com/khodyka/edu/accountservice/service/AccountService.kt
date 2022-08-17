package com.khodyka.edu.accountservice.service

import com.khodyka.edu.accountservice.domain.Account
import com.khodyka.edu.accountservice.domain.AccountStatus
import com.khodyka.edu.accountservice.domain.BalanceOperation
import com.khodyka.edu.accountservice.dto.AccountRequest
import com.khodyka.edu.accountservice.dto.AccountResponse
import com.khodyka.edu.accountservice.dto.ChangeAccountBalanceRequest
import com.khodyka.edu.accountservice.dto.ChangeAccountStatusRequest
import com.khodyka.edu.accountservice.repository.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun openAccount(accountRequest: AccountRequest) =
        Account(
            userId = accountRequest.userId
        ).let {
            accountRepository.save(it)
        }.let {
            AccountResponse(
                id = it.id!!,
                balance = it.balance
            )
        }

    fun changeAccountStatus(request: ChangeAccountStatusRequest) {
        when (request.newStatus) {
            AccountStatus.ACTIVE -> unblockAccount(request.id)
            AccountStatus.BLOCKED -> blockAccount(request.id)
        }
    }

    private fun blockAccount(id: UUID) {
        accountRepository.findByIdOrNull(id)?.let {
            if (it.accountStatus == AccountStatus.BLOCKED) return
            it.accountStatus = AccountStatus.ACTIVE
        } ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Account $id not found"
        )
    }

    private fun unblockAccount(id: UUID) {
        accountRepository.findByIdOrNull(id)?.let {
            if (it.accountStatus == AccountStatus.ACTIVE) return
            it.accountStatus = AccountStatus.ACTIVE
        } ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Account $id not found"
        )
    }

    fun changeAccountBalance(request: ChangeAccountBalanceRequest) {
        when (request.balanceOperation) {
            BalanceOperation.DECREASE -> decreaseAccountBalance(request)
            BalanceOperation.INCREASE -> increaseAccountBalance(request)
        }
    }

    private fun decreaseAccountBalance(request: ChangeAccountBalanceRequest) {
        accountRepository.findByIdOrNull(request.id)?.let {
            if (it.accountStatus == AccountStatus.BLOCKED) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Account ${request.id} is blocked")
            }

            val decreasingResult = it.balance.minus(request.amount)

            if (decreasingResult < BigDecimal.ZERO) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Account ${request.id} balance is too low")
            }

            it.balance = decreasingResult
        }
    }

    private fun increaseAccountBalance(request: ChangeAccountBalanceRequest) {
        accountRepository.findByIdOrNull(request.id)?.let {
            if (it.accountStatus == AccountStatus.BLOCKED) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Account ${request.id} is blocked")
            }

            it.balance = it.balance.plus(request.amount)
        }
    }


}
