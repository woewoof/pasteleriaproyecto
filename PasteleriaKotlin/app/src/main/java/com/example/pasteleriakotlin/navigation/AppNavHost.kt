package com.example.pasteleriakotlin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pasteleriakotlin.screens.AddProductScreen
import com.example.pasteleriakotlin.screens.LoginScreen
import com.example.pasteleriakotlin.screens.MainScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(onLogin = { navController.navigate("main") }) }
        composable("main") { MainScreen(onAdd = { navController.navigate("add") }) }
        composable("add") { AddProductScreen(onDone = { navController.popBackStack() }) }
    }
}
