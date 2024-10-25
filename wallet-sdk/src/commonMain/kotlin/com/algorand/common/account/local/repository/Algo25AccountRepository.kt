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

package com.algorand.common.account.local.repository

import com.algorand.common.account.local.model.LocalAccount
import kotlinx.coroutines.flow.Flow

internal interface Algo25AccountRepository {

    fun getAllAsFlow(): Flow<List<LocalAccount.Algo25>>

    fun getAccountCountAsFlow(): Flow<Int>

    suspend fun getAll(): List<LocalAccount.Algo25>

    suspend fun getAccount(address: String): LocalAccount.Algo25?

    suspend fun addAccount(account: LocalAccount.Algo25)

    suspend fun deleteAccount(address: String)

    suspend fun deleteAllAccounts()
}