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

package com.algorand.common.encryption

internal class AddressEncryptionManagerImpl : AddressEncryptionManager {
    override fun encrypt(address: String): String {
        // TODO Will be implemented at the end of the account refactor
        return address
    }

    override fun decrypt(encryptedAddress: String): String {
        // TODO Will be implemented at the end of the account refactor
        return encryptedAddress
    }
}