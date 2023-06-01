package com.example.employmenow.AppUI.screens

import android.annotation.SuppressLint
import android.app.Activity
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, googleViewModel: SharedGoogleViewModel, jobViewModel: JobViewModel) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var searchState = remember { mutableStateOf(false) }
    jobViewModel.getJobs() // Сервер пока что даёт таймаут
    val jobs by jobViewModel.jobs.observeAsState()
    val savedAccount: GoogleSignInAccount? by googleViewModel.googleAccount.observeAsState()
    val loadingStatus: LoadingStatus? by jobViewModel.loadingStatus.observeAsState()
    val permissionGranted = remember { mutableStateOf(true) }
    val context = LocalContext.current

    val filteredJobsState = remember { mutableStateOf(jobs ?: emptyList()) }

    LaunchedEffect(jobs) {
        jobs?.let { updatedJobs ->
            filteredJobsState.value = updatedJobs
        }
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
            savedAccount?.let {
                SideBar(sideBarScope = scope, scaffoldState = scaffoldState, navUploadCv = {navController.navigate(Screen.UploadCvScreen.route)},
                    it, { exitAccount() }
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
                Footer(
                    searchState = searchState,
                    isMainScreen = true,
                    navController = navController
                )
            }
        ) {
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
                                    onSearchStateChanged = { state -> searchState.value = state },
                                    jobs = jobs ?: emptyList(),
                                    onSearchPerformed = { updatedFilteredJobs ->
                                        updatedFilteredJobs?.let { filteredJobsList ->
                                            filteredJobsState.value = filteredJobsList
                                        }
                                    }
                                )
                            } else {
                                FilteredMenu(
                                    jobs = jobs ?: emptyList(),
                                    onSortedPerformed = { updatedSortedJobs ->
                                        updatedSortedJobs?.let { sortedJobsList ->
                                            filteredJobsState.value = sortedJobsList
                                        }
                                    }
                                )
                            }
                        }
                        items(filteredJobsState.value) { job ->
                            WorkListItem(item = job, onWorkClick = {
                                jobViewModel.getJobById(job.jobId)
                                navController.navigate(Screen.DescriptionScreen.route)
                            })
                        }
                    }
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
