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

package com.algorand.android.modules.accountblockpolling.data.di

import com.algorand.android.modules.accountblockpolling.data.mapper.ShouldRefreshRequestBodyMapper
import com.algorand.android.modules.accountblockpolling.data.local.AccountBlockPollingSingleLocalCache
import com.algorand.android.modules.accountblockpolling.data.repository.AccountBlockPollingRepositoryImpl
import com.algorand.android.modules.accountblockpolling.domain.repository.AccountBlockPollingRepository
import com.algorand.android.network.MobileAlgorandApi
import com.algorand.android.exceptions.RetrofitErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AccountBlockPollingModule {

    @Provides
    @Named(AccountBlockPollingRepository.INJECTION_NAME)
    fun provideAccountBlockPollingRepository(
        accountBlockPollingSingleLocalCache: AccountBlockPollingSingleLocalCache,
        mobileAlgorandApi: MobileAlgorandApi,
        retrofitErrorHandler: RetrofitErrorHandler,
        shouldRefreshRequestBodyMapper: ShouldRefreshRequestBodyMapper,
    ): AccountBlockPollingRepository {
        return AccountBlockPollingRepositoryImpl(
            accountBlockPollingSingleLocalCache = accountBlockPollingSingleLocalCache,
            mobileAlgorandApi = mobileAlgorandApi,
            retrofitErrorHandler = retrofitErrorHandler,
            shouldRefreshRequestBodyMapper = shouldRefreshRequestBodyMapper
        )
    }
}
