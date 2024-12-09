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

package com.algorand.common.deeplink.builder

import com.algorand.common.asset.AssetConstants.ALGO_ID
import com.algorand.common.asset.getSafeAssetIdForResponse
import com.algorand.common.deeplink.model.DeepLink
import com.algorand.common.deeplink.model.DeepLinkPayload
import com.algorand.common.deeplink.model.NotificationGroupType

internal class NotificationDeepLinkBuilder : DeepLinkBuilder {

    override fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean {
        return with(payload) {
            accountAddress != null &&
                assetId != null &&
                notificationGroupType != null &&
                amount == null &&
                walletConnectUrl == null &&
                url == null &&
                note == null &&
                xnote == null &&
                label == null &&
                webImportQrCode == null
        }
    }

    override fun createDeepLink(payload: DeepLinkPayload): DeepLink {
        return DeepLink.Notification(
            address = payload.accountAddress.orEmpty(),
            assetId = getSafeAssetIdForResponse(payload.assetId) ?: ALGO_ID,
            notificationGroupType = payload.notificationGroupType ?: NotificationGroupType.TRANSACTIONS
        )
    }
}