package com.example.employmenow.AppUI.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employmenow.Models.WorkModel
import com.example.employmenow.R
import androidx.compose.material3.*


@Composable
fun WorkListItem(item: WorkModel) {
    var isPressed by remember { mutableStateOf(item.isFavorite) }

    Row(
        Modifier
            .fillMaxWidth()
            .height(125.dp)
            .drawWithContent {
                drawLine(
                    color = Color(0xFFE0E0E0),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 4.dp.toPx()
                )
                drawContent()
                drawLine(
                    color = Color(0xFFE0E0E0),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            },
        verticalAlignment = Alignment.CenterVertically) {
        Column() {
            Image(
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .padding(start = 26.dp),
                painter = painterResource(id = R.drawable.page1),
                contentDescription = "")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            Modifier
                .width(180.dp)
                .height(110.dp)
                .padding(start = 20.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 1.dp),
                text = item.title,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.White)
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 9.dp),
                text = item.description,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.White)
            Row(
                Modifier.size(180.dp, 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier
                        .width(50.dp)
                        .background(Color(0xFF9F0C0C), RoundedCornerShape(4.dp))
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)


                ) {
                    Text(
                        text = "#high salary",
                        fontWeight = FontWeight.W600,
                        fontSize = 8.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.4.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center)
                }

                Box(
                    Modifier
                        .width(50.dp)
                        .background(Color(0xFF11AA20), RoundedCornerShape(4.dp))
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)

                ) {
                    Text(
                        text = "#part-time",
                        fontWeight = FontWeight.W600,
                        fontSize = 8.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.4.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center)
                }

                Box(
                    Modifier
                        .width(50.dp)
                        .background(Color(0xFFA0B710), RoundedCornerShape(4.dp))
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "#perks",
                        fontWeight = FontWeight.W600,
                        fontSize = 8.sp,
                        lineHeight = 16.sp,
                        letterSpacing = 0.4.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center)
                }
            }
        }
        Column(Modifier.padding(start = 50.dp, bottom = 3.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(onClick = { isPressed = !isPressed },
            content = {
                if(isPressed) Icon(painter = painterResource(id = R.drawable.star_full), contentDescription = "", tint = Color.Yellow) else Icon(painter = painterResource(id = R.drawable.star), contentDescription = "", tint = Color.Yellow)
            })

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "4/5",
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.Green,
                )
        }
    }
}