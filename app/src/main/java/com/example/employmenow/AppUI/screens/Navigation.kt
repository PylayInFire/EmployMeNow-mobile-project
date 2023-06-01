package com.example.employmenow.AppUI.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.employmenow.VM.JobViewModel
import com.example.employmenow.VM.SharedGoogleViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedVM: SharedGoogleViewModel = viewModel()
    val jobVM: JobViewModel = viewModel()
    NavHost(navController = navController,
            startDestination = Screen.SplashScreen.route) {

        composable(Screen.SplashScreen.route) { SplashScreen(navController = navController, sharedVM) }

        composable(Screen.SignUpScreen.route) { SignUpScreen(navController = navController, sharedVM) }

        composable(Screen.MainScreen.route) { MainScreen(navController = navController, sharedVM, jobVM) }

        composable(Screen.DescriptionScreen.route) { DescriptionScreen(navController = navController, jobVM) }

        composable(Screen.ResponsesScreen.route) { NotificationScreen(navController = navController) }

        composable(Screen.UploadCvScreen.route) { CvUpload(navController = navController)}


    }

}