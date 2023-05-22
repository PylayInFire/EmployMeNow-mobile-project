package com.example.employmenow.AppUI.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.employmenow.AppUI.components.*
import com.example.employmenow.Models.WorkModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    var searchState = remember { mutableStateOf(false) }
    val data = listOf(
        WorkModel("HR Specialist", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", true),
        WorkModel("Manager", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", false),
        WorkModel("Cleaner", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", true),
        WorkModel("Manager", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", false),
        WorkModel("Manager", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", false),
        WorkModel("Manager", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", false),
        WorkModel("Manager", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", false),
        WorkModel("Manager", "Need an HR Specialist? " +
                "Our team provides tailored HR solutions...", false)
    )

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
                LazyColumn(
                    Modifier
                        .background(Color(0xFF272727))
                        .padding(top = 50.dp, bottom = 66.dp)
                        .fillMaxSize()
                ) {
                    item {
                        /*if (searchState.value) SearchBar(
                            searchState = searchState,
                            onSearchStateChanged = { state -> searchState.value = state }
                        ) else FilteredMenu()*/
                    }
                    itemsIndexed(data) { index, data ->
                        WorkListItem(item = data)
                    }
                }
            }
        }
    )
}