package com.example.employmenow.AppUI.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.employmenow.VM.JobViewModel
import com.example.employmenow.VM.SharedGoogleViewModel
import com.example.employmenow.VM.WorkerViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedVM: SharedGoogleViewModel = viewModel()
    val jobVM: JobViewModel = viewModel()
    val workersVM: WorkerViewModel = viewModel()
    NavHost(navController = navController,
            startDestination = Screen.SplashScreen.route) {

        composable(Screen.SplashScreen.route) { SplashScreen(navController = navController, sharedVM) }

        composable(Screen.SignUpScreen.route) { SignUpScreen(navController = navController, sharedVM) }

        composable(Screen.MainScreen.route) { MainScreen(navController = navController, sharedVM, jobVM, workersVM) }

        composable(Screen.DescriptionScreen.route) { DescriptionScreen(navController = navController, jobVM,  workersVM) }

        composable(Screen.ResponsesScreen.route) { NotificationScreen(navController = navController) }

        composable(Screen.UploadCvScreen.route) { CvUpload(navController = navController)}

        composable(Screen.ProfileScreen.route) { ProfileScreen(navController = navController, workersVM)}


    }

}