package com.example.toastyyy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.toasty.BottomToast
import com.example.toasty.TopToast
import com.example.toastyyy.ui.theme.ToastyyyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToastyyyTheme {
                TopToast()
                BottomToast()
            }
        }
    }
}

@Preview
@Composable
fun AnimatedCircleToBoxPreview() {
}

@Composable
fun showAlertDialog() {
    val openDialog = remember { mutableStateOf(true) }

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    openDialog.value = false
                },
            ) {
                Text("Confirm")
            }
        },
    )
}
