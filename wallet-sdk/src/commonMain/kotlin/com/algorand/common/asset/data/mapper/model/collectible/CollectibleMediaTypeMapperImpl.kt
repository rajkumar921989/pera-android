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

package com.algorand.common.asset.data.mapper.model.collectible

import com.algorand.common.asset.data.database.model.CollectibleMediaTypeEntity
import com.algorand.common.asset.data.model.collectible.CollectibleMediaTypeResponse
import com.algorand.common.asset.domain.model.CollectibleMediaType

internal class CollectibleMediaTypeMapperImpl : CollectibleMediaTypeMapper {

    override fun invoke(response: CollectibleMediaTypeResponse): CollectibleMediaType {
        return when (response) {
            CollectibleMediaTypeResponse.IMAGE -> CollectibleMediaType.IMAGE
            CollectibleMediaTypeResponse.VIDEO -> CollectibleMediaType.VIDEO
            CollectibleMediaTypeResponse.MIXED -> CollectibleMediaType.MIXED
            CollectibleMediaTypeResponse.AUDIO -> CollectibleMediaType.AUDIO
            CollectibleMediaTypeResponse.UNKNOWN -> CollectibleMediaType.UNKNOWN
        }
    }

    override fun invoke(entity: CollectibleMediaTypeEntity): CollectibleMediaType {
        return when (entity) {
            CollectibleMediaTypeEntity.IMAGE -> CollectibleMediaType.IMAGE
            CollectibleMediaTypeEntity.VIDEO -> CollectibleMediaType.VIDEO
            CollectibleMediaTypeEntity.MIXED -> CollectibleMediaType.MIXED
            CollectibleMediaTypeEntity.AUDIO -> CollectibleMediaType.AUDIO
            CollectibleMediaTypeEntity.UNKNOWN -> CollectibleMediaType.UNKNOWN
        }
    }
}
