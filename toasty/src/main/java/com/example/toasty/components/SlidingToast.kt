package com.example.toasty.components

import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
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
fun SlidingToast(
    modifier: Modifier = Modifier,
    messageType: MessageType = MessageType.DEFAULT,
    message: String = "An unexpected error occurred. Please try again later",
    duration: Long = 2000,
    height: Dp = 160.dp,
    width: Dp? = null,
    textColor: Color = Color.White,
    fontSize: TextUnit = 16.sp,
    cornerRadius: Dp = 12.dp,
    fromTop: Boolean = true,
    onDismissCallback: @Composable () -> Unit = {},
) {
    var hasTransitionStarted by remember { mutableStateOf(false) }
    var clipShape by remember { mutableStateOf(CircleShape) }
    var slideAnimation by remember { mutableStateOf(true) }
    var animationStarted by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }
    var dismissCallback by remember { mutableStateOf(false) }

    val displayMetrics: DisplayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidthInDp = width ?: (displayMetrics.widthPixels / displayMetrics.density).dp

    val boxWidth by animateDpAsState(
        targetValue = if (hasTransitionStarted) screenWidthInDp else 30.dp,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = "Box width",
    )

    val boxHeight by animateDpAsState(
        targetValue = if (hasTransitionStarted) height else 30.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "Box height",
    )

    val slideY by animateDpAsState(
        targetValue = if (slideAnimation) {
            if (fromTop) (-100).dp else 100.dp  // Slide from top or bottom
        } else 0.dp,
        animationSpec = tween(durationMillis = 200),
        label = "Slide parameter in DP",
    )

    if (!animationStarted) {
        LaunchedEffect(Unit) {
            slideAnimation = false

            // Delay for 0.33 seconds before transitioning to rectangle
            delay(330)
            hasTransitionStarted = true
            clipShape = RoundedCornerShape(cornerRadius)
            showMessage = true

            // Delay for 2 seconds or user input before reverting to circle
            delay(duration)
            hasTransitionStarted = false
            showMessage = false

            // Delay for 0.33 seconds before sliding up
            delay(330)
            clipShape = CircleShape
            slideAnimation = true
            animationStarted = true
            dismissCallback = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(16.dp),
    ) {
        Box(
            modifier = modifier
                .size(boxWidth, boxHeight)
                .offset(y = slideY)
                .clip(clipShape)
                .background(getColorForMessageType(messageType))
                .align(if (fromTop) Alignment.TopCenter else Alignment.BottomCenter),
            contentAlignment = Alignment.Center,
        ) {
            if (showMessage) {
                Text(
                    text = message,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp),
                )
            }

            if (dismissCallback) onDismissCallback()
        }
    }
}

