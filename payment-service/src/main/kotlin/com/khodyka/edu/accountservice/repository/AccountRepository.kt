package com.khodyka.edu.accountservice.repository

import com.khodyka.edu.accountservice.domain.Account
import com.khodyka.edu.accountservice.domain.AccountStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByIdAndAccountStatus(id: UUID, accountStatus: AccountStatus): Account?
}
