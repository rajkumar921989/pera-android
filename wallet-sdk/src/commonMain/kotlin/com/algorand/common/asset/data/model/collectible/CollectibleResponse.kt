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

package com.algorand.common.asset.data.model.collectible

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CollectibleResponse(
    @SerialName("standard") val standard: CollectibleStandardTypeResponse?,
    @SerialName("media_type") val mediaType: CollectibleMediaTypeResponse?,
    @SerialName("primary_image") val primaryImageUrl: String?,
    @SerialName("title") val title: String?,
    @SerialName("collection") val collection: CollectionResponse?,
    @SerialName("media") val collectibleMedias: List<CollectibleMediaResponse>?,
    @SerialName("description") val description: String?,
    @SerialName("traits") val traits: List<CollectibleTraitResponse>?
)