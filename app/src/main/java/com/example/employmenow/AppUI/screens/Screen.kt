package com.example.employmenow.AppUI.screens

sealed class Screen(var route: String) {
    object SplashScreen : Screen("splash_screen")
    object SignUpScreen: Screen("signup_screen")
    object MainScreen: Screen("main_screen")
    object DescriptionScreen: Screen("description_screen")
    object UploadCvScreen: Screen("uploadcv_screen")
    object FavoritesScreen: Screen("favorites_screen")
    object ResponsesScreen: Screen("responses_screen")
    object SettingsScreen: Screen("settings_screen")
    object ProfileScreen: Screen("profile_screen")
    object AboutUsScreen: Screen("aboutus_screen")
}
