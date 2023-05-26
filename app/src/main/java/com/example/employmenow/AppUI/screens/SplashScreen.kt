package com.example.employmenow.AppUI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.employmenow.R
import com.example.employmenow.VM.SharedGoogleViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController, googleViewModel: SharedGoogleViewModel) {
    var LoadPercent by remember { mutableStateOf(0f) };
    val screenWith = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current

    LaunchedEffect(LoadPercent) {
        while (LoadPercent < 1f) {
            delay(50)
            LoadPercent += 0.05f
        }

        if (LoadPercent >= 1f) {
            val account = GoogleSignIn.getLastSignedInAccount(context) // сохраненные данные об аккаунте в виде объекта
            if(account == null) {
                navController.navigate(
                    route = Screen.SignUpScreen.route,
                    builder = {
                        navController.popBackStack(Screen.SplashScreen.route, inclusive = true)
                    }
                )
            } else {
                googleViewModel.setGoogleAccount(account)
                navController.navigate(
                    route = Screen.MainScreen.route,
                    builder = {
                        navController.popBackStack(Screen.SplashScreen.route, inclusive = true)
                    }
                )
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(modifier = Modifier.size(173.dp, 201.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "d")
        Spacer(modifier = Modifier.padding(top = 182.dp))
        Box(modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(Color(0xFF385682))
            .size(screenWith * 0.8f, 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(screenWith * LoadPercent)
                    .background(Color.Green)
            )
        }
        Spacer(modifier = Modifier.padding(top = 21.dp))
        Text(
            text = "${((LoadPercent * 100).toInt())}% completed",
            fontWeight = FontWeight.W500,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.4.sp,
            color = Color(0xFFC7DDFF))
    }
}