/*
 *   ~ Copyright 2022 Pera Wallet, LDA
 *   ~ Licensed under the Apache License, Version 2.0 (the "License");
 *   ~ you may not use this file except in compliance with the License.
 *   ~ You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *   ~ Unless required by applicable law or agreed to in writing, software
 *   ~ distributed under the License is distributed on an "AS IS" BASIS,
 *   ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   ~ See the License for the specific language governing permissions and
 *   ~ limitations under the License
 *   -->
 */

package com.algorand.android.modules.assetinbox.detail.receivedetail.domain.mapper

import com.algorand.android.modules.assetinbox.detail.receivedetail.domain.model.Arc59RejectTransactionPayload
import com.algorand.android.modules.assetinbox.detail.receivedetail.ui.model.Arc59ReceiveDetailNavArgs
import javax.inject.Inject

class Arc59RejectTransactionPayloadMapperImpl @Inject constructor() : Arc59RejectTransactionPayloadMapper {

    override fun invoke(args: Arc59ReceiveDetailNavArgs): Arc59RejectTransactionPayload {
        return Arc59RejectTransactionPayload(
            receiverAddress = args.receiverAddress,
            creatorAccountAddress = args.creatorAccountAddress,
            inboxAccountAddress = args.inboxAccountAddress,
            assetId = args.assetDetail.id,
            isClaimingAlgo = args.shouldUseFundsBeforeRejecting
        )
    }
}
