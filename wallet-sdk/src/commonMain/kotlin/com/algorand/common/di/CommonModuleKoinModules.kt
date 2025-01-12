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

import com.algorand.common.account.detail.di.accountDetailKoinModule
import com.algorand.common.account.info.di.accountInformationKoinModule
import com.algorand.common.account.local.di.localAccountsKoinModule
import com.algorand.common.asset.di.assetDetailKoinModules
import com.algorand.common.block.di.blockPollingKoinModule
import com.algorand.common.cache.di.cacheKoinModule
import com.algorand.common.encryption.di.encryptionModule
import com.algorand.common.utils.date.dateKoinModule

val commonModuleKoinModules = listOf(
    localAccountsKoinModule,
    encryptionModule,
    platformKoinModule(),
    accountInformationKoinModule,
    networkKoinModule,
    dateKoinModule,
    blockPollingKoinModule,
    cacheKoinModule,
    accountDetailKoinModule
) + assetDetailKoinModules
