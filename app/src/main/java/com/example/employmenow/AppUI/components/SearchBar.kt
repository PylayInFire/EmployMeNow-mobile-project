package com.example.employmenow.AppUI.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.employmenow.R
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.employmenow.AppUI.screens.MainScreen
import com.example.employmenow.Models.JobModel
import com.example.employmenow.Utils.VoiceRecognition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchState: MutableState<Boolean>, onSearchStateChanged: (Boolean) -> Unit, jobs: List<JobModel>, onSearchPerformed: (List<JobModel>) -> Unit) {
    var searchText by remember { mutableStateOf("") }
    var originalJobs by remember { mutableStateOf(jobs) }
    val startVoiceRecognition = rememberLauncherForActivityResult(VoiceRecognition()) { result ->
        searchText = result
        Search(searchText, originalJobs, onSearchPerformed)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(74.dp)
            .background(Color(0xFF272727))
            .padding(start = 23.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(modifier = Modifier
            .size(21.dp, 18.dp),
             painter = painterResource(id = R.drawable.search),
             contentDescription = "SearchIcon", tint = Color(1f, 1f, 1f, 0.74f))
        Spacer(modifier = Modifier.width(32.dp))
        TextField(
            value = searchText,
            modifier = Modifier.width(230.dp),
            onValueChange = { newValue: String ->
                searchText = newValue
                Search(searchText, originalJobs, onSearchPerformed)
            },
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
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(onClick = { startVoiceRecognition.launch(0) }) {
            Icon(modifier = Modifier.size(16.dp, 18.dp),
                painter = painterResource(id = R.drawable.micro),
                contentDescription = "voice", tint = Color(0xFF979797)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = {
            val searchState = !searchState.value
            onSearchStateChanged(searchState)
            if (!searchState) {
                searchText = ""
                Search(searchText, originalJobs, onSearchPerformed)
            }
        }) {
            Icon(modifier = Modifier.size(14.dp, 16.dp), painter = painterResource(id = R.drawable.cross), contentDescription = "close", tint = Color(0xFF979797))
        }
    }

}

fun Search(searchText: String, originalJobs: List<JobModel>, onSearchPerformed: (List<JobModel>) -> Unit) {
    val filteredJobs = if (searchText.isNotEmpty()) {
        originalJobs.filter { it.jobName.contains(searchText, ignoreCase = true) }
    } else {
        originalJobs
    }
    onSearchPerformed(filteredJobs)
}

