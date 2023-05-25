package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.employmenow.AppUI.components.Footer
import com.example.employmenow.AppUI.components.Header
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import com.example.employmenow.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CvUpload(navController: NavController) {
    Scaffold(
        topBar = { Header(isMainScreen = false, navController = navController, {})},
        content = {
          CvFragment()
        },
        bottomBar = { Footer(isMainScreen = false, navController = navController) }
    )

}

@Composable
fun CvFragment() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF272727))
                .padding(top = 188.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.pdfdoc),
                contentDescription = "your pdf"
            )
            Text(
                modifier = Modifier.padding(top = 67.dp),
                text = "Please upload your CV in PDF format",
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                letterSpacing = 0.4.sp,
                color = Color.White
            )

            Button(
                modifier = Modifier
                    .padding(top = 87.dp)
                    .size(236.dp, 57.dp),
                onClick = { },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color(0xFF3CF283),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Upload your CV",
                    fontWeight = FontWeight.W600,
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    letterSpacing = 0.4.sp,
                    color = Color(0xFF000000)
                )
            }
        }
    }
}