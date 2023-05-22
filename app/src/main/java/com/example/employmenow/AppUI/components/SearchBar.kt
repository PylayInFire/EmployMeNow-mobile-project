package com.example.employmenow.AppUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.employmenow.AppUI.screens.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchState: MutableState<Boolean>, onSearchStateChanged: (Boolean) -> Unit) {
    var searchText by remember { mutableStateOf("") }
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
            onValueChange = { newValue: String -> searchText = newValue },
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
        Spacer(modifier = Modifier.width(20.dp))
        Icon(modifier = Modifier.size(16.dp, 18.dp), painter = painterResource(id = R.drawable.micro), contentDescription = "voice", tint = Color(0xFF979797))
        Spacer(modifier = Modifier.width(25.dp))
        IconButton(onClick = {
            val searchState = !searchState.value
            onSearchStateChanged(searchState)
        }) {
            Icon(modifier = Modifier.size(17.dp, 18.dp), painter = painterResource(id = R.drawable.cross), contentDescription = "close", tint = Color(0xFF979797))
        }
    }
}