package com.algorand.common.ui.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.algorand.common.ui.theme.PeraTheme

@Composable
fun BlackButtonWidget(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Black),
        modifier =
        Modifier
            .width(327.dp)
            .height(52.dp)
    ) {
        Text(
            text = title,
            style = PeraTheme.typography.body.regular.sans,
            color = Color.White)
    }
}