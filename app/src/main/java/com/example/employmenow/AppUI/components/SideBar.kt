package com.example.employmenow.AppUI.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import com.example.employmenow.R
import androidx.compose.material.ButtonColors
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun SideBar(
    sideBarScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navUploadCv: () -> Unit
) {
    Box(modifier = Modifier
        .width(346.dp)
        .fillMaxHeight()) {

        Column(Modifier.fillMaxSize()) {
            SideBarHeader()
            Box(modifier = Modifier.weight(1f)) {
                SideBarContent(sideBarScope, scaffoldState, navUploadCv)
            }
            SideBarFooter()
        }
    }
}

@Preview
@Composable
fun SideBarHeader() {
    Row(
        modifier = Modifier
            .height(112.dp)
            .fillMaxWidth()
            .background(Color(0xFF8C8C8C)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 22.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.test_photo),
                contentDescription = "ff"
            )
            Text(
                modifier = Modifier.padding(top = 7.dp),
                text = "Yevhen Pylaikin",
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.05.sp,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .width(65.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "ddd",
                    tint = Color.White
                )
            }
        }
    }

}


@Composable
fun SideBarContent(sideBarScope: CoroutineScope, scaffoldState: ScaffoldState, navUploadCv: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF525151))
            .padding(top = 89.dp, start = 16.dp, end = 16.dp)
    ) {
        SideBarItem(
            icon = R.drawable.home,
            text = "Home",
            onClick = {
                sideBarScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        )


        Spacer(modifier = Modifier.height(70.dp))
        SideBarItem(icon = R.drawable.uploadpdf, text = "Upload CV", onClick = {
            navUploadCv()
            sideBarScope.launch {
                scaffoldState.drawerState.close()
            }
        })
        Spacer(modifier = Modifier.height(70.dp))
        SideBarItem(icon = R.drawable.aboutus, text = "About Us", onClick = {})
        Spacer(modifier = Modifier.height(70.dp))
        SideBarItem(icon = R.drawable.qanda, text = "Q&A", onClick = {})
    }
}

@Preview
@Composable
fun SideBarFooter() {
    Row (
        modifier = Modifier
            .height(89.dp)
            .fillMaxWidth()
            .background(Color(0xFF8C8C8C)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(modifier = Modifier.padding(start = 28.dp),onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.settings), contentDescription = "fd", tint = Color.White)
        }

        IconButton(modifier = Modifier.padding(end = 36.dp), onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.exit), contentDescription = "dsf", tint = Color.White)
        }
    }
}


@Composable
fun SideBarItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF726D72))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(modifier = Modifier.padding(start = 22.dp),
                text = text,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp,
                lineHeight = 29.sp,
                letterSpacing = 0.05.sp,
                color = Color(0xFFFFFFFF))
            Icon(modifier = Modifier.padding(end = 97.dp), painter = painterResource(icon), contentDescription = "dfssd", tint = Color.White)
        }
    }

}