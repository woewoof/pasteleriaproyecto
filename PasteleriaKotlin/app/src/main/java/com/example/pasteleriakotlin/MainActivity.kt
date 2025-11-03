package com.example.pasteleriakotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.pasteleriakotlin.ui.theme.PasteleriaTheme
import com.example.pasteleriakotlin.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasteleriaTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
