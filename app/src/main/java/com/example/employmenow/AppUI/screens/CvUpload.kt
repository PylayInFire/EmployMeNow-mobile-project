package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.employmenow.AppUI.components.Footer
import com.example.employmenow.AppUI.components.Header
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import com.example.employmenow.R
import com.example.employmenow.VM.FileUploadViewModel
import com.example.employmenow.VM.WorkerViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CvUpload(navController: NavController, workerViewModel: WorkerViewModel) {
    Scaffold(
        topBar = { Header(isMainScreen = false, navController = navController, {})},
        content = {
          CvFragment()
        },
        bottomBar = { workerViewModel.feedbackCount.value?.let { Footer(isMainScreen = false, navController = navController, favoriteJobs = {}, feedbacksCount = it) } }
    )

}

@Composable
fun CvFragment() {
    val fileUploadVM: FileUploadViewModel = viewModel()
    val context = LocalContext.current
    val progress = fileUploadVM.uploadProgress.observeAsState()
    val pickPdfLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            fileUploadVM.uploadFile(uri, context)
        }
    }
    val animatedProgress = animateFloatAsState(
        targetValue = (progress.value?.div(100)?.toFloat()) ?: 0f,
        animationSpec = tween(durationMillis = 750)
    )
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
                onClick = { pickPdfLauncher.launch("application/pdf") },
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

            Box(modifier = Modifier
                .padding(top = 40.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xFF385682))
                .fillMaxWidth()
                .height(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(animatedProgress.value)
                        .background(Color.Green)
                )
            }
        }
    }
}
