package com.example.toasty.components

import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toasty.MessageType
import com.example.toasty.getColorForMessageType
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun StaticToast(
    message: String = "An unexpected error occurred. Please try again later",
    messageType: MessageType = MessageType.DEFAULT,
    duration: Long = 2000,
    height: Dp = 160.dp,
    width: Dp? = null,
    textColor: Color = Color.White,
    fontSize: TextUnit = 16.sp,
    cornerRadius: Dp = 12.dp,
    onDismissCallback: @Composable () -> Unit = {}
) {
    var showMessage by remember { mutableStateOf(true) }
    val dismissCallback by remember { mutableStateOf(false) }

    val displayMetrics: DisplayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidthInDp = width ?: (displayMetrics.widthPixels / displayMetrics.density).dp

    val clipShape = RoundedCornerShape(cornerRadius)

    LaunchedEffect(showMessage) {
        if (showMessage) {
            delay(duration)
            showMessage = false
        }
    }

    if (showMessage) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(clipShape)
                .size(width = screenWidthInDp, height = height)
                .background(getColorForMessageType(messageType))
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = fontSize
            )

            if (dismissCallback) onDismissCallback()
        }
    }
}