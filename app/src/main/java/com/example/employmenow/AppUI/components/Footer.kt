package com.example.employmenow.AppUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.employmenow.AppUI.screens.MainScreen
import com.example.employmenow.AppUI.screens.Screen
import com.example.employmenow.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Footer(searchState: MutableState<Boolean> = mutableStateOf(false),
           isMainScreen: Boolean = true,
           navController: NavController) {

    var countOfMessage by remember {
        mutableStateOf(0)
    }

    BottomNavigation(
        Modifier.size(428.dp, 56.dp),
        backgroundColor = Color(0xFF424242)
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {},
            icon = { Icon(modifier = Modifier.size(20.dp, 20.dp), painter = painterResource(id = R.drawable.like), contentDescription = "", tint = Color.White) },
            label = { Text(
                text = "Favorites",
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color(1f, 1f, 1f, 0.74f)) }
        )
        if(isMainScreen) {
            BottomNavigationItem(
                selected = false,
                onClick = { searchState.value = !searchState.value },
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp, 20.dp),
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        text = "Search",
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.4.sp,
                        color = Color(1f, 1f, 1f, 0.74f)
                    )
                }
            )
        } else {
            BottomNavigationItem(
                selected = false,
                onClick = { navController.navigate(Screen.MainScreen.route) { popUpTo(Screen.MainScreen.route) { inclusive = true }} },
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp, 20.dp),
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        text = "Home",
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.4.sp,
                        color = Color(1f, 1f, 1f, 0.74f)
                    )
                }
            )
        }
        BottomNavigationItem(
            selected = false,
            onClick = { navController.navigate(Screen.ResponsesScreen.route) {
                popUpTo(Screen.ResponsesScreen.route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }},
            icon = {
                Box(modifier = Modifier.size(20.dp)) {
                    BadgedBox(badge = {
                        Badge(Modifier.size(15.dp), containerColor = Color(0xFFF42B1E)) {
                            Text(
                                text = countOfMessage.toString(),
                                fontWeight = FontWeight.W500,
                                fontSize = 10.sp,
                                lineHeight = 16.sp,
                                letterSpacing = 0.4.sp,
                                color = Color.White
                            )
                        }
                    }) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.notifications),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            },
            label = { Text(
                text = "Notifications",
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color(1f, 1f, 1f, 0.74f)) }
        )
    }
}