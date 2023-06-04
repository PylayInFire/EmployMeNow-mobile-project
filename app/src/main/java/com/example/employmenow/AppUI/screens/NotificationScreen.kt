package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.employmenow.Models.Feedback
import kotlinx.coroutines.launch
import com.example.employmenow.R
import com.example.employmenow.VM.WorkerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationScreen (navController: NavController, workerViewModel: WorkerViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold (
      topBar = { Header(isMainScreen = false, navController = navController, {})},
      bottomBar = { workerViewModel.feedbackCount.value?.let { Footer(isMainScreen = false, navController = navController, favoriteJobs = {}, feedbacksCount = it) } },
      content = {
          Column(modifier = Modifier.padding(top = 15.dp)) {
              JobResponse(acceptedJobs = workerViewModel.acceptedJobs.value ?: emptyList(), declinedJobs = workerViewModel.declinedJobs.value ?: emptyList())
          }
      }
    )
}

@Composable
/*Сделал специально чтобы вначале отображались вакансии на которые приняли а потом на которые отклонили*/
fun JobResponse(
    acceptedJobs: List<Feedback>,
    declinedJobs: List<Feedback>
) {
    Spacer(modifier = Modifier.height(55.dp))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF373636))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (acceptedJobs.isNotEmpty() || declinedJobs.isNotEmpty()) {
                Text(
                    text = "Jobs",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                LazyColumn {
                    items(acceptedJobs.size) { index->
                        JobItem(acceptedJobs[index], accepted = true)
                    }
                    items(declinedJobs.size) { index ->
                        JobItem(declinedJobs[index], accepted = false)
                    }
                }
            } else {
                Text(
                    text = "No jobs available",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun JobItem(job: Feedback, accepted: Boolean) {
    Row(
        modifier = Modifier
            .padding(bottom = 25.dp)
            .height(117.dp)
            .fillMaxWidth()
            .background(Color(0xFF272727)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.page1),
            modifier = Modifier
                .padding(start = 17.dp)
                .size(58.dp, 50.dp),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .padding(start = 15.dp, bottom = 18.dp)
                .weight(1f)
        ) {
            job.job?.jobName?.let {
                Text(
                    modifier = Modifier.padding(start = 62.dp),
                    text = it,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    letterSpacing = 0.4.sp,
                    color = Color.White
                )
            }

            Text(
                text = if (accepted) "Вашу заявку на работу рассмотрели и кажется вы подходите" else "Кажется вас отклонили, но не рассраивайтесь и поищите другую вакансию",
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
                    tint = if (accepted) Color.Red else Color.Green
                )
            }
        }
    }
}