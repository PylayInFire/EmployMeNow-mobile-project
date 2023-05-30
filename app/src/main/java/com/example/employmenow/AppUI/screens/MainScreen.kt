package com.example.employmenow.AppUI.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.employmenow.AppUI.components.*
import com.example.employmenow.Models.JobModel
import com.example.employmenow.Models.WorkModel
import com.example.employmenow.VM.AuthViewModel
import com.example.employmenow.VM.JobViewModel
import com.example.employmenow.VM.Status.LoadingStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var searchState = remember { mutableStateOf(false) }
    val jobVM: JobViewModel = viewModel()
    jobVM.getJobs() 
    val jobs by jobVM.jobs.observeAsState()
    val loadingStatus: LoadingStatus? by jobVM.loadingStatus.observeAsState()

    

    ModalDrawer(
        drawerContent = {
            SideBar(sideBarScope = scope, scaffoldState = scaffoldState, navUploadCv = {navController.navigate(Screen.UploadCvScreen.route)})
        },
        drawerState = scaffoldState.drawerState,
        content = {
            Scaffold(
                topBar = { Header(isMainScreen = true, navController = navController, onMenuClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) },
                bottomBar = {
                    Footer(
                        searchState = searchState,
                        onSearchStateChanged = { state -> searchState.value = state },
                        isMainScreen = true,
                        navController = navController
                    )
                }
            ) {
                when(loadingStatus) {
                    is LoadingStatus.Loading -> {
                       Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                Modifier
                                    .size(50.dp)
                                    .align(Alignment.Center),
                                color = Color(0xFF3CF283))
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
                                if (searchState.value) SearchBar(
                                    searchState = searchState,
                                    onSearchStateChanged = { state -> searchState.value = state }
                                ) else FilteredMenu()
                            }
                            items(jobs ?: emptyList()) { job ->
                                WorkListItem(item = job)
                            }
                        }
                    }
                }
            }
        }
    )
}