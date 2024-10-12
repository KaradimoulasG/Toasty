package com.example.toastyyy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.toasty.components.SlidingToast
import com.example.toasty.components.StaticToast
import com.example.toastyyy.ui.theme.ToastyyyTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToastyyyTheme {
                StaticToast()
            }
        }
    }
}