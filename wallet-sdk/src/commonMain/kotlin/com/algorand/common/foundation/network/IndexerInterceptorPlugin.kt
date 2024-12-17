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

package com.algorand.common.foundation.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.headers
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.set
import io.ktor.http.takeFrom
import io.ktor.util.AttributeKey
import io.ktor.util.appendAll

internal class IndexerInterceptorPlugin(
    private val getIndexerInterceptorConfig: GetIndexerInterceptorConfig
) : HttpClientPlugin<Unit, IndexerInterceptorPlugin> {

    override val key: AttributeKey<IndexerInterceptorPlugin> = AttributeKey("IndexerInterceptor")

    override fun prepare(block: Unit.() -> Unit): IndexerInterceptorPlugin {
        return IndexerInterceptorPlugin(getIndexerInterceptorConfig)
    }

    override fun install(plugin: IndexerInterceptorPlugin, scope: HttpClient) {
        scope.sendPipeline.intercept(HttpSendPipeline.Before) {
            val config = plugin.getIndexerInterceptorConfig()
            context.url.setNodeAwareUrl(config)
            context.setNodeAwareHeaders(config)
            proceed()
        }
    }

    private fun URLBuilder.setNodeAwareUrl(config: IndexerInterceptorPluginConfig) {
        val newUrl = URLBuilder(config.baseUrl)
        newUrl.pathSegments = pathSegments
        newUrl.parameters.appendAll(parameters)
        set {
            takeFrom(newUrl)
        }
    }

    private fun HttpMessageBuilder.setNodeAwareHeaders(config: IndexerInterceptorPluginConfig) {
        headers {
            append("X-Indexer-API-Token", config.apiKey)
        }
    }
}

data class IndexerInterceptorPluginConfig(
    val baseUrl: String,
    val apiKey: String
)
