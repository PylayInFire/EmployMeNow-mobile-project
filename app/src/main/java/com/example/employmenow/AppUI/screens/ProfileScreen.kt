package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.employmenow.VM.FileUploadViewModel
import com.example.employmenow.VM.WorkerViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.employmenow.AppUI.components.Footer
import com.example.employmenow.AppUI.components.Header


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController, workerViewModel: WorkerViewModel) {
    Scaffold(
        topBar = { Header(isMainScreen = false, navController = navController, {}) },
        content = {
            ProfileFragment()
        },
        bottomBar = { workerViewModel.feedbackCount.value?.let { Footer(isMainScreen = false, navController = navController, favoriteJobs = {}, feedbacksCount = it) } }
    )
}

@Composable
fun ProfileFragment() {
    val fileUploadVM: FileUploadViewModel = viewModel()
    val context = LocalContext.current
    val selectedImage = remember { mutableStateOf<Uri?>(null) }
    val pickImgLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            fileUploadVM.uploadFile(uri, context)
            selectedImage.value = uri
        }
    }
    Column(Modifier
        .fillMaxSize()
        .background(Color(0xFF272727))
        .padding(top = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp, 70.dp)
                .background(color = Color.Blue, shape = CircleShape)
        ) {
            selectedImage.value?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(context = context)
                            .data(data = uri)
                            .build()
                    ),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Transparent),
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Button(
            modifier = Modifier
                .padding(top = 87.dp)
                .size(236.dp, 57.dp),
            onClick = { pickImgLauncher.launch("image/*") },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color(0xFF3CF283),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Upload your photo",
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                lineHeight = 30.sp,
                letterSpacing = 0.4.sp,
                color = Color(0xFF000000)
            )
        }
    }
}