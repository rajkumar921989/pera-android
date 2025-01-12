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

package com.algorand.android.modules.staking

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.algorand.android.BuildConfig.STAKING_MAINNET_URL
import com.algorand.android.BuildConfig.STAKING_TESTNET_URL
import com.algorand.android.discover.common.ui.model.WebViewError
import com.algorand.android.discover.home.domain.model.DappInfo
import com.algorand.android.modules.card.CardsFragmentArgs
import com.algorand.android.modules.currency.domain.usecase.CurrencyUseCase
import com.algorand.android.modules.perawebview.GetAuthorizedAddressesWebMessage
import com.algorand.android.modules.perawebview.GetDeviceIdWebMessage
import com.algorand.android.modules.perawebview.ParseOpenSystemBrowserUrl
import com.algorand.android.modules.perawebview.ui.BasePeraWebViewViewModel
import com.algorand.android.modules.staking.model.StakingPreview
import com.algorand.android.usecase.GetIsActiveNodeTestnetUseCase
import com.algorand.android.utils.Event
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StakingViewModel @Inject constructor(
    private val getIsActiveNodeTestnetUseCase: GetIsActiveNodeTestnetUseCase,
    private val getAuthorizedAddressesWebMessage: GetAuthorizedAddressesWebMessage,
    private val getDeviceIdWebMessage: GetDeviceIdWebMessage,
    private val parseOpenSystemBrowserUrl: ParseOpenSystemBrowserUrl,
    private val currencyUseCase: CurrencyUseCase,
    private val gson: Gson,
    savedStateHandle: SavedStateHandle
) : BasePeraWebViewViewModel() {

    private val args = CardsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _stakingPreviewFlow = MutableStateFlow<StakingPreview>(StakingPreview())
    val stakingPreviewFlow: StateFlow<StakingPreview?>
        get() = _stakingPreviewFlow.asStateFlow()

    fun getStakingUrl(): String {
        val stakingBaseUrl = if (isConnectedToTestnet())
            STAKING_TESTNET_URL
        else
            STAKING_MAINNET_URL

        return "$stakingBaseUrl/${args.path.orEmpty()}"
    }

    fun getAuthorizedAddresses() {
        viewModelScope.launch {
            val authAddressesMessage = getAuthorizedAddressesWebMessage()
            _stakingPreviewFlow.update {
                it.copy(sendMessageEvent = Event(authAddressesMessage))
            }
        }
    }

    fun getDeviceId() {
        viewModelScope.launch {
            val deviceIdMessage = getDeviceIdWebMessage() ?: return@launch
            _stakingPreviewFlow.update {
                it.copy(sendMessageEvent = Event(deviceIdMessage))
            }
        }
    }

    override fun onPageFinished(title: String?, url: String?) {
        super.onPageFinished(title, url)
        _stakingPreviewFlow.value = _stakingPreviewFlow.value.copy(onPageFinished = Event(Unit))
    }

    override fun onError() {
        viewModelScope.launch {
            _stakingPreviewFlow.update {
                it.copy(errorEvent = Event(WebViewError.NO_CONNECTION))
            }
        }
    }

    override fun onHttpError() {
        viewModelScope.launch {
            _stakingPreviewFlow.update {
                it.copy(errorEvent = Event(WebViewError.HTTP_ERROR))
            }
        }
    }

    fun getOpenDappWebview(jsonPayload: String): DappInfo? {
        return gson.fromJson(jsonPayload, DappInfo::class.java)
    }

    fun getOpenSystemBrowserUrl(jsonPayload: String): String? {
        return parseOpenSystemBrowserUrl(jsonPayload)
    }

    fun getPrimaryCurrencyId(): String {
        return currencyUseCase.getPrimaryCurrencyId()
    }

    fun isConnectedToTestnet(): Boolean {
        return getIsActiveNodeTestnetUseCase.invoke()
    }
}
