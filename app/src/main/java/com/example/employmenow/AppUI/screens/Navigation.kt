package com.example.employmenow.AppUI.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
            startDestination = Screen.SplashScreen.route) {

        composable(Screen.SplashScreen.route) { SplashScreen(navController = navController) }

        composable(Screen.SignUpScreen.route) { SignUpScreen(navController = navController) }

        composable(Screen.MainScreen.route) { MainScreen(navController = navController) }

        composable(Screen.ResponsesScreen.route) { NotificationScreen(navController = navController) }

        composable(Screen.UploadCvScreen.route) { CvUpload(navController = navController)}

    }

}