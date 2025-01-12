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

package com.algorand.common.encryption.di

import com.algorand.common.encryption.AddressEncryptionManager
import com.algorand.common.encryption.AddressEncryptionManagerImpl
import com.algorand.common.encryption.Base64Manager
import com.algorand.common.encryption.Base64ManagerImpl
import com.algorand.common.encryption.SecretKeyEncryptionManager
import com.algorand.common.encryption.SecretKeyEncryptionManagerImpl
import org.koin.dsl.module

val encryptionModule = module {
    single<Base64Manager> { Base64ManagerImpl() }
    single<SecretKeyEncryptionManager> { SecretKeyEncryptionManagerImpl() }
    single<AddressEncryptionManager> { AddressEncryptionManagerImpl() }
}
