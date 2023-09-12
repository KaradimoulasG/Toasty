package com.example.toastyyy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.toasty.BottomToast
import com.example.toasty.MessageType
import com.example.toasty.TopToast
import com.example.toastyyy.ui.theme.ToastyyyTheme

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
                    BottomToast(MessageType.DEFAULT, "something")
                }
            }
        }
    }
}

@Preview
@Composable
fun AnimatedCircleToBoxPreview() {
    BottomToast(MessageType.DEFAULT, "cool")
}
