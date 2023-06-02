package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.employmenow.AppUI.components.Header
import com.example.employmenow.VM.JobViewModel
import com.example.employmenow.R
import com.example.employmenow.VM.WorkerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DescriptionScreen(
    navController: NavController,
    jobViewModel: JobViewModel,
    workerViewModel: WorkerViewModel
) {
    val jobById by jobViewModel.selectedJobById.observeAsState()
    val hasCV by workerViewModel.hasCv.observeAsState()

    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Header(
                isMainScreen = false,
                navController = navController,
                {}
            )
        }
    ) {
        jobById?.let { job ->
            hasCV?.let { hasCV ->
                MainJobFragment(
                    jobName = job.jobName,
                    jobDescription = job.description,
                    hasCV = hasCV,
                    navController =  navController,
                    workerViewModel = workerViewModel,
                    jobId = job.jobId,
                )
            }
        }
    }

}

@SuppressLint("SuspiciousIndentation")
@Composable
fun MainJobFragment(
    jobName: String,
    jobDescription: String,
    hasCV: Boolean,
    navController: NavController,
    workerViewModel: WorkerViewModel,
    jobId: String
    ) {
    var feedBackText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
        Box(
            Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
                .verticalScroll(scrollState)
                .background(Color(0xFF373636))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(50.dp)
            ) {
                Text(
                    text = jobName,
                    fontWeight = FontWeight.W400,
                    fontSize = 24.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.4.sp,
                    color = Color.White
                )
                Image(
                    modifier = Modifier.size(250.dp, 250.dp),
                    painter = painterResource(id = R.drawable.page3),
                    contentDescription = "WorkImage"
                )
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .width(maxWidth)
                            .background(Color(0xFF272727))
                            .padding(horizontal = 16.dp)
                        , verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = "Job Description",
                            fontWeight = FontWeight.W800,
                            fontSize = 20.sp,
                            lineHeight = 30.sp,
                            letterSpacing = 0.4.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = jobDescription,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            lineHeight = 30.sp,
                            letterSpacing = 0.4.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Text(
                    text = "Write your feelings",
                    fontWeight = FontWeight.W800,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    letterSpacing = 0.4.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = feedBackText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
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
                TextButton(onClick = {
                              if(hasCV) {
                                  workerViewModel.giveFeedback(jobId, feedBackText)
                              } else {
                                  navController.navigate(Screen.UploadCvScreen.route) {
                                      popUpTo(Screen.MainScreen.route)
                                  }
                              }
                }, modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF3CF283), shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp),
                ) {
                    Text(
                        text = "Apply Now",
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        lineHeight = 30.sp,
                        letterSpacing = 0.4.sp
                    )
                }
            }
        }
}