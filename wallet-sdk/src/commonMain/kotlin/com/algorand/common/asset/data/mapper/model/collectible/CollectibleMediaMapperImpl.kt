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

import com.algorand.common.asset.data.database.model.CollectibleMediaEntity
import com.algorand.common.asset.data.model.collectible.CollectibleMediaResponse
import com.algorand.common.asset.domain.model.CollectibleMedia

internal class CollectibleMediaMapperImpl(
    private val collectibleMediaTypeMapper: CollectibleMediaTypeMapper,
    private val collectibleMediaTypeExtensionMapper: CollectibleMediaTypeExtensionMapper
) : CollectibleMediaMapper {

    override fun invoke(response: CollectibleMediaResponse): CollectibleMedia? {
        return with(response) {
            if (mediaType == null && downloadUrl == null && previewUrl == null && mediaTypeExtension == null) {
                return null
            }
            CollectibleMedia(
                mediaType = mediaType?.let { collectibleMediaTypeMapper(it) },
                downloadUrl = downloadUrl,
                previewUrl = previewUrl,
                mediaTypeExtension = mediaTypeExtension?.let { collectibleMediaTypeExtensionMapper(it) }
            )
        }
    }

    override fun invoke(entities: List<CollectibleMediaEntity>): List<CollectibleMedia> {
        return entities.map { entity ->
            with(entity) {
                CollectibleMedia(
                    mediaType = collectibleMediaTypeMapper(mediaType),
                    downloadUrl = downloadUrl,
                    previewUrl = previewUrl,
                    mediaTypeExtension = collectibleMediaTypeExtensionMapper(mediaTypeExtension)
                )
            }
        }
    }
}