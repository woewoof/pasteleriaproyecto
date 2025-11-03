package com.example.pasteleriakotlin.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun PasteleriaTheme(content: @Composable ()->Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = {
            Surface {
                content()
            }
        }
    )
}
