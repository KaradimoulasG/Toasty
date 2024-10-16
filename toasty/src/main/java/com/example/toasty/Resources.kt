package com.example.toasty

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun getColorForMessageType(messageType: MessageType): Color {
    return when (messageType) {
        MessageType.SUCCESS -> Color.Green
        MessageType.ERROR -> Color.Red
        MessageType.DYNAMIC -> {
            when (isSystemInDarkTheme()) {
                true -> dynamicDarkColorScheme(LocalContext.current).background
                false -> dynamicLightColorScheme(LocalContext.current).background
            }
        }
        MessageType.DEFAULT -> MaterialTheme.colorScheme.primary
    }
}
