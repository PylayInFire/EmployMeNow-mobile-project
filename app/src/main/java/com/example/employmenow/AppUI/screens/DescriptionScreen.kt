package com.example.employmenow.AppUI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.employmenow.Models.JobModel
import com.example.employmenow.VM.JobViewModel
import com.example.employmenow.R

@Composable
fun DescriptionScreen(
    navController: NavController,
    jobViewModel: JobViewModel
) {
    val jobById by jobViewModel.selectedJobById.observeAsState()
    var feedBackText by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(50.dp)) {
        jobById?.let { Text(text = it.jobName, color = Color.White) }
        Image(painter = painterResource(id = R.drawable.page3), contentDescription = "WorkImage")
        jobById?.let { Text(text = it.description, textAlign = TextAlign.Center) }
        TextField(
            value = feedBackText,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            onValueChange = { newValue: String -> feedBackText = newValue },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF333333),
                unfocusedContainerColor = Color(0xFF272727),
                disabledContainerColor = Color.Red,
                cursorColor = Color(0xFF9D9C9C),
                focusedIndicatorColor = Color(0xFF5BEE58)
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 18.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.White
            ),
        )
        TextButton(onClick = {  }) {
            Text(text = "Apply now")
        }
    }

}