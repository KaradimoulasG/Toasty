package com.example.toasty

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

enum class MessageType {
    SUCCESS,
    ERROR,
    DEFAULT,
    DYNAMIC,
}

@Composable
fun getColorForMessageType(messageType: MessageType): Color {
    return when (messageType) {
        MessageType.SUCCESS -> Color.Green
        MessageType.ERROR -> Color.Red
        MessageType.DYNAMIC -> {
            when (isSystemInDarkTheme()) {
                true -> dynamicDarkColorScheme(LocalContext.current).primaryContainer
                false -> dynamicLightColorScheme(LocalContext.current).primaryContainer
            }
        }
        MessageType.DEFAULT -> MaterialTheme.colorScheme.primary
    }
}
