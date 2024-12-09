/*
 * Copyright 2022 Pera Wallet, LDA
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.algorand.common.account.local.domain.usecase

import com.algorand.common.account.local.domain.model.LocalAccount
import com.algorand.common.account.local.domain.repository.Algo25AccountRepository
import com.algorand.common.account.local.domain.repository.Bip39AccountRepository
import com.algorand.common.account.local.domain.repository.LedgerBleAccountRepository
import com.algorand.common.account.local.domain.repository.NoAuthAccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

internal class GetLocalAccountsUseCase(
    private val bip39AccountRepository: Bip39AccountRepository,
    private val algo25AccountRepository: Algo25AccountRepository,
    private val ledgerBleAccountRepository: LedgerBleAccountRepository,
    private val noAuthAccountRepository: NoAuthAccountRepository,
    private val dispatcher: CoroutineDispatcher
) : GetLocalAccounts {

    override suspend fun invoke(): List<LocalAccount> {
        return withContext(dispatcher) {
            val deferredBip39Accounts = async { bip39AccountRepository.getAll() }
            val deferredAlgo25Accounts = async { algo25AccountRepository.getAll() }
            val deferredLedgerBleAccounts = async { ledgerBleAccountRepository.getAll() }
            val deferredNoAuthAccounts = async { noAuthAccountRepository.getAll() }
            awaitAll(
                deferredBip39Accounts,
                deferredAlgo25Accounts,
                deferredLedgerBleAccounts,
                deferredNoAuthAccounts
            ).flatten()
        }
    }
}
