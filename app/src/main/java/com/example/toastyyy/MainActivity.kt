package com.example.toastyyy

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toastyyy.ui.theme.ToastyyyTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToastyyyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    SlideThenExpandThenShrinkThenSlide(MessageType.ERROR)
                }
            }
        }
    }
}

@Preview
@Composable
fun AnimatedCircleToBoxPreview() {
    SlideThenExpandThenShrinkThenSlide(MessageType.SUCCESS)
}

@Composable
fun SlideThenExpandThenShrinkThenSlide(
    messageType: MessageType = MessageType.SUCCESS,
    message: String = "Firfiriki s agapw",
) {
    var isTransitionStarted by remember { mutableStateOf(false) }
    var clipShape by remember { mutableStateOf(CircleShape) }
    var slideDownAnimation by remember { mutableStateOf(true) }
    var animationStarted by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    val displayMetrics: DisplayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidthInDp = (displayMetrics.widthPixels / displayMetrics.density).dp

    val width by animateDpAsState(
        targetValue = if (isTransitionStarted) screenWidthInDp else 30.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "",
    )

    val height by animateDpAsState(
        targetValue = if (isTransitionStarted) 160.dp else 30.dp,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "",
    )

    val slideY by animateDpAsState(
        targetValue = if (slideDownAnimation) (-100).dp else 0.dp,
        animationSpec = tween(durationMillis = 200),
        label = "",
    )

    if (!animationStarted) {
        LaunchedEffect(Unit) {
            slideDownAnimation = false

            // Delay for 2 seconds before transitioning to rectangle
            delay(330)
            isTransitionStarted = true
            clipShape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp)
            showMessage = true

            // Delay for 1 second before reverting to circle
            delay(2500)
            isTransitionStarted = false
            showMessage = false

            // Delay for 0.33 seconds before sliding down
            delay(330)
            clipShape = CircleShape
            slideDownAnimation = true
            animationStarted = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center, // Center content within this Box
        ) {
            Box(
                modifier = Modifier
                    .size(width, height)
                    .offset(y = slideY)
                    .clip(clipShape)
                    .background(getColorForMessageType(messageType)),
                contentAlignment = Alignment.Center,
            ) {
                if (showMessage) {
                    Text(
                        text = message,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                when (isSystemInDarkTheme()) {
                    true -> dynamicDarkColorScheme(LocalContext.current).background
                    false -> dynamicLightColorScheme(LocalContext.current).background
                }
            } else {
                MaterialTheme.colorScheme.primary
            }
        }
        MessageType.DEFAULT -> MaterialTheme.colorScheme.primary
    }
}
