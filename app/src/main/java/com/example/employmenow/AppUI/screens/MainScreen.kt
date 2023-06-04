package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.employmenow.AppUI.components.*
import com.example.employmenow.Utils.PermissionUtils
import com.example.employmenow.VM.JobViewModel
import com.example.employmenow.VM.SharedGoogleViewModel
import com.example.employmenow.VM.Status.LoadingStatus
import com.example.employmenow.VM.WorkerViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, googleViewModel: SharedGoogleViewModel, jobViewModel: JobViewModel, workerViewModel: WorkerViewModel) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var searchState = remember { mutableStateOf(false) }
    val jobs by jobViewModel.jobs.observeAsState()
    val favoriteJobs by jobViewModel.favoriteJobs.observeAsState()
    val savedAccount: GoogleSignInAccount? by googleViewModel.googleAccount.observeAsState()
    val loadingStatus: LoadingStatus? by jobViewModel.loadingStatus.observeAsState()
    val isBanned by workerViewModel.isBanned.observeAsState()
    val avatar by workerViewModel.avatar.observeAsState("")
    val permissionGranted = remember { mutableStateOf(true) }
    val feedbacksCount by workerViewModel.feedbackCount.observeAsState()
    val acceptedJobs by workerViewModel.acceptedJobs.observeAsState()
    val declinedJobs by workerViewModel.declinedJobs.observeAsState()
    var isFavoriteJobs by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val ban = remember {
        mutableStateOf(false)
    }

    val filteredJobsState = remember { mutableStateOf(jobs ?: emptyList()) }

    LaunchedEffect(isBanned) {
        ban.value = isBanned == true
    }

    LaunchedEffect(Unit) {
        jobViewModel.getJobs()
        savedAccount?.email?.let {
            try {
                workerViewModel.getUserDataByEmail(it)
                workerViewModel.savedEmail(it)
                workerViewModel.getFeedbackCounts()
                workerViewModel.getFeedbackJobs()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }


    LaunchedEffect(jobs, favoriteJobs, isFavoriteJobs) {
        val filteredJobs = if (isFavoriteJobs) favoriteJobs ?: emptyList() else jobs ?: emptyList()
        filteredJobsState.value = filteredJobs
        jobViewModel.updateFavoriteJobs()

    }

    fun exitAccount() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(context, gso)
        client.signOut()
        navController.navigate(Screen.SignUpScreen.route) {
            popUpTo(Screen.MainScreen.route) {
                inclusive = true
            }
        }
    }

    LaunchedEffect(Unit) {
        permissionGranted.value = PermissionUtils.checkPermissions(context as Activity)
    }



    ModalDrawer(
        drawerContent = {
            savedAccount?.let { it ->
                    SideBar(sideBarScope = scope, scaffoldState = scaffoldState, navUploadCv = {navController.navigate(Screen.UploadCvScreen.route)}, navProfile = {navController.navigate(Screen.ProfileScreen.route)},
                        avatar,
                        it, { exitAccount() },
                    )
            }
        },
        drawerState = scaffoldState.drawerState
    ) {
        Scaffold(
            topBar = {
                Header(isMainScreen = true, navController = navController, onMenuClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
            },
            bottomBar = {
                feedbacksCount?.let {
                    Footer(
                        searchState = searchState,
                        isMainScreen = true,
                        navController = navController,
                        favoriteJobs = { isFavoriteJobs = !isFavoriteJobs },
                        feedbacksCount = it
                    )
                }
            }
        ) {
            if(!ban.value) {
                when (loadingStatus) {
                    is LoadingStatus.Loading -> {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center),
                                color = Color(0xFF3CF283)
                            )
                        }
                    }

                    is LoadingStatus.Error -> {
                        Text(text = "Error")
                    }

                    is LoadingStatus.Success -> {
                        LazyColumn(
                            Modifier
                                .background(Color(0xFF272727))
                                .padding(top = 50.dp, bottom = 66.dp)
                                .fillMaxSize()
                        ) {
                            item {
                                if (searchState.value) {
                                    SearchBar(
                                        searchState = searchState,
                                        onSearchStateChanged = { state ->
                                            searchState.value = state
                                        },
                                        jobs = filteredJobsState.value,
                                        onSearchPerformed = { updatedFilteredJobs ->
                                            updatedFilteredJobs?.let { filteredJobsList ->
                                                filteredJobsState.value = filteredJobsList
                                            }
                                        }
                                    )
                                } else {
                                    FilteredMenu(
                                        jobs = filteredJobsState.value,
                                        onSortedPerformed = { updatedSortedJobs ->
                                            updatedSortedJobs?.let { sortedJobsList ->
                                                filteredJobsState.value = sortedJobsList
                                            }
                                        }
                                    )
                                }
                            }
                            items(filteredJobsState.value) { job ->
                                val isFavorite = favoriteJobs?.contains(job) == true
                                WorkListItem(item = job,
                                    isFavorite = isFavorite,
                                    onWorkClick = {
                                    jobViewModel.getJobById(job.jobId)
                                    navController.navigate(Screen.DescriptionScreen.route)
                                    }, onIconClick = {
                                    if(job.isFavorite) {
                                        job.isFavorite = false
                                        workerViewModel.removeFromFavorite(job.jobId)
                                    } else {
                                        job.isFavorite = true
                                        workerViewModel.addToFavorite(job.jobId)
                                    }
                                })
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "You were banned", color = Color.White)
                }
            }
            if (!permissionGranted.value) {
                PermissionModal(
                    requestPermissions = { PermissionUtils.requestPermissions(context as Activity) },
                    onPermissionGranted = { permissionGranted.value = true }
                )
            }
        }
    }
}


