package com.algorand.common.account.ui.screens

import algorand_android.wallet_sdk.generated.resources.Res
import algorand_android.wallet_sdk.generated.resources.ic_key
import algorand_android.wallet_sdk.generated.resources.ic_left_arrow
import algorand_android.wallet_sdk.generated.resources.recover_an_algorand
import algorand_android.wallet_sdk.generated.resources.recover_in_the_following
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.algorand.common.ui.theme.PeraTheme
import com.algorand.common.ui.widgets.BlackButtonWidget
import com.algorand.common.ui.widgets.TopAppBarWidget
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun RecoverAccountInfoScreen(
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Blue),
        topBar = {
            TopAppBarWidget(
                title = "",
                image = vectorResource(Res.drawable.ic_left_arrow),
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                // .background(PeraTheme.colors.background)
                .padding(top = 100.dp),
        ) {
            // Image
            Icon(
                imageVector = vectorResource(Res.drawable.ic_key),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = Color.Cyan

            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = stringResource(Res.string.recover_an_algorand),
                style = PeraTheme.typography.body.regular.sansBold,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = stringResource(Res.string.recover_in_the_following),
                style = PeraTheme.typography.body.regular.sans,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            BlackButtonWidget(
                title = stringResource(Res.string.recover_an_algorand),
                onClick = {
                    isBottomSheetVisible = true
                }
            )

            if (isBottomSheetVisible) {
                AccountTypeBottomSheet(isBottomSheetVisible) {
                    isBottomSheetVisible = false  // Callback to hide bottom sheet
                }
            }
        }
    }
}