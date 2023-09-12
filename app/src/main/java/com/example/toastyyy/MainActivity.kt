package com.example.toastyyy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.toastyyy.ui.theme.ToastyyyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToastyyyTheme {
            }
        }
    }
}

@Preview
@Composable
fun AnimatedCircleToBoxPreview() {
}
