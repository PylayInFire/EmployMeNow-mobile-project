package com.example.employmenow.AppUI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.employmenow.AppUI.components.Footer
import com.example.employmenow.AppUI.components.Header
import kotlinx.coroutines.launch
import com.example.employmenow.R

@Composable
fun NotificationScreen (navController: NavController) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold (
      topBar = { Header(isMainScreen = false, navController = navController, {})},
      bottomBar = { Footer(isMainScreen = false, navController = navController)},
      content = {}
    )
}

@Composable
fun JobResponse(
    title: String,
    description: String,
    image: ImageBitmap
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF373636))) {
        Row(
            modifier = Modifier
                .height(117.dp)
                .fillMaxWidth()
                .background(Color(0xFF272727)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 17.dp)
                    .size(58.dp, 50.dp),
                bitmap = image,
                contentDescription = ""
            )

            Column(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 18.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(start = 62.dp),
                    text = title,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.4.sp,
                    color = Color.White
                )

                Text(
                    text = description,
                    fontWeight = FontWeight.W400,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    letterSpacing = 0.4.sp,
                    color = Color.White
                )
            }

            Box(
                modifier = Modifier.padding(bottom = 70.dp)
            ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cross),
                        contentDescription = "cross",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}