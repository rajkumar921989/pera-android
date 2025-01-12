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

package com.algorand.common.di

import com.algorand.common.account.local.data.database.AccountDatabase
import com.algorand.common.account.local.data.database.getAccountDatabase
import com.algorand.common.algosdk.AlgoAccountSdk
import com.algorand.common.algosdk.AlgoAccountSdkImpl
import com.algorand.common.foundation.database.PeraDatabase
import com.algorand.common.foundation.database.getPeraDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformKoinModule(): Module = module {
    single<AccountDatabase> { getAccountDatabase() }
    single<AlgoAccountSdk> { AlgoAccountSdkImpl() }
    single<PeraDatabase> { getPeraDatabase() }
}
