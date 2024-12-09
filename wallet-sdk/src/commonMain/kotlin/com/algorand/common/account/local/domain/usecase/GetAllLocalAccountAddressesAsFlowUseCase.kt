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

import com.algorand.common.account.local.domain.repository.Algo25AccountRepository
import com.algorand.common.account.local.domain.repository.Bip39AccountRepository
import com.algorand.common.account.local.domain.repository.LedgerBleAccountRepository
import com.algorand.common.account.local.domain.repository.NoAuthAccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class GetAllLocalAccountAddressesAsFlowUseCase(
    private val bip39AccountRepository: Bip39AccountRepository,
    private val algo25AccountRepository: Algo25AccountRepository,
    private val ledgerBleAccountRepository: LedgerBleAccountRepository,
    private val noAuthAccountRepository: NoAuthAccountRepository
) : GetAllLocalAccountAddressesAsFlow {

    override fun invoke(): Flow<List<String>> {
        return combine(
            bip39AccountRepository.getAllAsFlow(),
            algo25AccountRepository.getAllAsFlow(),
            ledgerBleAccountRepository.getAllAsFlow(),
            noAuthAccountRepository.getAllAsFlow()
        ) { bip39Accounts, algo25Accounts, ledgerBleAccounts, noAuthAccounts ->
            buildList {
                addAll(bip39Accounts.map { it.address })
                addAll(algo25Accounts.map { it.address })
                addAll(ledgerBleAccounts.map { it.address })
                addAll(noAuthAccounts.map { it.address })
            }
        }
    }
}
