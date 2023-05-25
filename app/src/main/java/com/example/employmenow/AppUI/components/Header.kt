package com.example.employmenow.AppUI.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employmenow.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.employmenow.Utils.ShareUtils
import kotlinx.coroutines.launch

@Composable
fun Header(
    isMainScreen: Boolean,
    navController: NavController,
    onMenuClick: () -> Unit
) {
    val context = LocalContext.current



    var isOpenMenu by remember {
        mutableStateOf(false)
    }
    val share: () -> Unit = {
        ShareUtils.shareText(context, "Присылаю очень крутое приложение EmployMeNow")
    }
    TopAppBar(
        title = { Row(
            modifier = Modifier.padding(start = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Your Image",
                modifier = Modifier.size(33.dp, 37.dp)
            )
            Spacer(modifier = Modifier.width(27.dp))
            Text(
                text = "Employ me Now",
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.05.sp,
                color = Color.White
            )
        } },
        navigationIcon = {
            if(isMainScreen) {
                IconButton(onClick = { onMenuClick() }) {
                    Icon(painter = painterResource(id = R.drawable.burgermenu), contentDescription = "Menu", tint = Color.White)
                }
            } else {
                IconButton(onClick = { navController.popBackStack()}) {
                    Icon(painter = painterResource(id = R.drawable.navigateback), contentDescription = "Back", tint = Color.White)
                }
            }
        },
        actions = {
            IconButton(onClick = { isOpenMenu = !isOpenMenu }) {
                Icon(
                    modifier = Modifier.size(87.dp, 27.dp),
                    painter = painterResource(id = R.drawable.dotmenu),
                    contentDescription = "Add menu",
                    tint = Color.White
                )
            }
            DropdownMenu(expanded = isOpenMenu, onDismissRequest = { isOpenMenu = false }, modifier = Modifier.background(Color(0xFF424242))) {
                DropdownMenuItem(onClick = { share() }) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Share",
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            letterSpacing = 0.05.sp,
                            color = Color.White)
                        Icon(painter = painterResource(id = R.drawable.share), contentDescription = "fsdf", tint = Color.White)
                    }
                }
            }
        }, backgroundColor = Color(0xFF424242))
}