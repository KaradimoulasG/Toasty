package com.example.toasty

import android.util.DisplayMetrics
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TopToast(
    modifier: Modifier = Modifier,
    messageType: MessageType = MessageType.DEFAULT,
    message: String = "An unexpected error occurred. Please try again later",
    height: Dp = 160.dp,
    width: Dp? = null,
    onDismissCallback: @Composable () -> Unit = {},
) {
    var hasTransitionStarted by remember { mutableStateOf(false) }
    var clipShape by remember { mutableStateOf(CircleShape) }
    var slideDownAnimation by remember { mutableStateOf(true) }
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
        targetValue = if (slideDownAnimation) (-100).dp else 0.dp,
        animationSpec = tween(durationMillis = 200),
        label = "Slide parameter in DP",
    )

    if (!animationStarted) {
        LaunchedEffect(Unit) {
            slideDownAnimation = false

            // Delay for 0.33 seconds before transitioning to rectangle
            delay(330)
            hasTransitionStarted = true
            clipShape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)
            showMessage = true

            // Delay for 2 seconds before reverting to circle
            delay(2000)
            hasTransitionStarted = false
            showMessage = false

            // Delay for 0.33 seconds before sliding up
            delay(330)
            clipShape = CircleShape
            slideDownAnimation = true
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
                .align(alignment = Alignment.TopCenter),
            contentAlignment = Alignment.Center,
        ) {
            if (showMessage) {
                Text(
                    text = message,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp),
                )
            }

            if (dismissCallback) onDismissCallback()
        }
    }
}

@Composable
fun BottomToast(
    modifier: Modifier = Modifier,
    messageType: MessageType = MessageType.DEFAULT,
    message: String = "An unexpected error occurred. Please try again later",
    height: Dp = 160.dp,
    width: Dp? = null,
    onDismissCallback: @Composable () -> Unit = {},
) {
    var isTransitionStarted by remember { mutableStateOf(false) }
    var clipShape by remember { mutableStateOf(CircleShape) }
    var slideAnimation by remember { mutableStateOf(true) }
    var animationStarted by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }
    var dismissCallback by remember { mutableStateOf(false) }

    val displayMetrics: DisplayMetrics = LocalContext.current.resources.displayMetrics
    val screenHeightInDp = (displayMetrics.heightPixels / displayMetrics.density).dp
    val screenWidthInDp = width ?: (displayMetrics.widthPixels / displayMetrics.density).dp

    val boxWidth by animateDpAsState(
        targetValue = if (isTransitionStarted) screenWidthInDp else 30.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "Box width",
    )

    val boxHeight by animateDpAsState(
        targetValue = if (isTransitionStarted) height else 30.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "Box Height",
    )

    val slideY by animateDpAsState(
        targetValue = if (slideAnimation) 100.dp else 0.dp,
        animationSpec = tween(durationMillis = 200),
        label = "Slide parameter in DP",
    )

    if (!animationStarted) {
        LaunchedEffect(Unit) {
            slideAnimation = false

            // Delay for 0.33 seconds before transitioning to rectangle
            delay(330)
            isTransitionStarted = true
            clipShape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)
            showMessage = true

            // Delay for 2 seconds before transitioning back to circle
            delay(2000)
            isTransitionStarted = false
            showMessage = false

            // Delay for 0.33 seconds before sliding down
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
                .align(alignment = Alignment.BottomCenter),
            contentAlignment = Alignment.Center,
        ) {
            if (showMessage) {
                Text(
                    text = message,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp),
                )
            }

            if (dismissCallback) onDismissCallback()
        }
    }
}