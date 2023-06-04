package com.example.employmenow.AppUI.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.decode.DecodeUtils
import com.example.employmenow.Models.Avatar
import kotlinx.coroutines.CoroutineScope
import com.example.employmenow.R
import com.example.employmenow.Utils.Decoder
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.launch


@Composable
fun SideBar(
    sideBarScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navUploadCv: () -> Unit,
    navProfile: () -> Unit,
    avatar: String?,
    account: GoogleSignInAccount,
    exitAccount: () -> Unit
) {
    val bitmap = avatar?.let { Decoder.decodeToBitmap(it) }
    val image = bitmap?.asImageBitmap()
    Box(modifier = Modifier
        .width(346.dp)
        .fillMaxHeight()) {
        
        Column(Modifier.fillMaxSize()) {
            SideBarHeader(account = account, navProfile, image)
            Box(modifier = Modifier.weight(1f)) {
                SideBarContent(sideBarScope, scaffoldState, navUploadCv)
            }
            SideBarFooter(exitAccount)
        }
    }
}


@Composable
fun SideBarHeader(account: GoogleSignInAccount, navProfile: () -> Unit, imageBitmap: ImageBitmap?) {
    val defaultBitmap: Bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.test_photo)
    val defaultImageBitmap: ImageBitmap = defaultBitmap.asImageBitmap()
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
                bitmap = imageBitmap ?: defaultImageBitmap,
                modifier = Modifier
                    .size(70.dp, 70.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent),
                contentDescription = "Profile pic"
            )
            Text(
                modifier = Modifier.padding(top = 7.dp),
                text = "${account.givenName} ${account.familyName}",
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
            IconButton(onClick = { navProfile() }) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Profile settings",
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


@Composable
fun SideBarFooter(
    exitAccount: () -> Unit
) {

    Row (
        modifier = Modifier
            .height(89.dp)
            .fillMaxWidth()
            .background(Color(0xFF8C8C8C)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        
        IconButton(modifier = Modifier.padding(start = 28.dp),onClick = {  }) {
            Icon(painter = painterResource(id = R.drawable.settings), contentDescription = "settings", tint = Color.White)
        }
        
        IconButton(modifier = Modifier.padding(end = 36.dp), onClick = {
            exitAccount()
        }) {
            Icon(painter = painterResource(id = R.drawable.exit), contentDescription = "exit", tint = Color.White)
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