package com.algorand.common.account.ui.screens

import algorand_android.wallet_sdk.generated.resources.Res
import algorand_android.wallet_sdk.generated.resources.ic_close
import algorand_android.wallet_sdk.generated.resources.ic_left_arrow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.algorand.common.ui.theme.PeraTheme
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTypeBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(isVisible) {
        if (isVisible) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    if (isVisible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            //windowInsets = WindowInsets(bottom = paddingValues.calculateBottomPadding()),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column {
                CoreActionItem()
            }
        }
    }
}

@Composable
private fun CoreActionItem(
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Icon(
                imageVector = vectorResource(Res.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier.size(20.dp),

                )
            Text(
                text = "Select your Mnemonic type",
                style = PeraTheme.typography.body.regular.sansBold,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        // Card 1
        Card(
            modifier = Modifier
                .fillMaxWidth().padding(top = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),

            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {

            Row(Modifier.padding(all = 16.dp)) {
                Text(
                    text = "Bip39",
                    style = PeraTheme.typography.body.regular.sansBold,
                )
                Text(
                    text = "Recommended",
                    style = PeraTheme.typography.body.regular.sans,
                    modifier = Modifier
                        .background(
                            color = Color.LightGray, // Set the background color to gray
                            shape = RoundedCornerShape(20.dp) // Optional: Add rounded corners
                        )
                        .padding(start = 5.dp),
                    color = Color.White// Add padding for better readability
                )
            }
            Row(modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New inter-operable format that enables important features like HD Wallet",
                    style = PeraTheme.typography.body.regular.sans,
                )
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_left_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                )
            }
            Text(
                text = "24 Key mnemonic keys",
                style = PeraTheme.typography.body.regular.sans,
                color = Color.Blue,
                modifier = Modifier.padding(all = 16.dp)

            )

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            elevation = CardDefaults.cardElevation(8.dp),

            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),

            ) {
            Column(Modifier.padding(all=16.dp)) {
                Text(
                    text = "Algo25",
                    style = PeraTheme.typography.body.regular.sansBold,
                )
                Row {
                    Text(
                        text = "Legacy Format that is specific to Algorand ecosystem",
                        style = PeraTheme.typography.body.regular.sans,
                    )
                    Icon(
                        imageVector = vectorResource(Res.drawable.ic_left_arrow),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                }

            }

            Text(
                text = "25 Key mnemonic keys",
                style = PeraTheme.typography.body.regular.sans,
                color = Color.Blue,
                modifier = Modifier.padding(all = 16.dp)
            )
        }
    }
}