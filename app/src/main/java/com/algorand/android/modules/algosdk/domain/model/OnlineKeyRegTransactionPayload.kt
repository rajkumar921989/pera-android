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

package com.algorand.android.modules.algosdk.domain.model

import com.algorand.android.models.TransactionParams

data class OnlineKeyRegTransactionPayload(
    val senderAddress: String,
    val selectionPublicKey: String,
    val stateProofKey: String,
    val voteKey: String,
    val voteFirstRound: String,
    val voteLastRound: String,
    val voteKeyDilution: String,
    val txnParams: TransactionParams,
    val note: String?
)
