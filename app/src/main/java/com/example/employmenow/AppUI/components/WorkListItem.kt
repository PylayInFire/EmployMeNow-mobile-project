package com.example.employmenow.AppUI.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.employmenow.Models.JobModel


@Composable
fun WorkListItem(item: JobModel, onWorkClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .height(145.dp)
            .clickable {
                onWorkClick()
            }
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
                .height(135.dp)
                .padding(start = 20.dp)) {
            Text(
                modifier = Modifier.padding(bottom = 1.dp),
                text = item.jobName,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.White)
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 9.dp),
                text = if(item.description.length > 40) "${item.description.substring(0, 40)}..." else item.description,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
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
                    item.tags.getOrNull(0)?.let {
                        Text(
                            text = it.tagName,
                            modifier = Modifier.align(Alignment.Center),
                            fontWeight = FontWeight.W600,
                            fontSize = 9.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.4.sp,
                            color = Color.White,
                        )
                    }
                }
                
                Box(
                    Modifier
                        .width(50.dp)
                        .background(Color(0xFF11AA20), RoundedCornerShape(4.dp))
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)

                ) {
                    item.tags.getOrNull(1)?.let {
                        Text(
                            text = it.tagName,
                            modifier = Modifier.align(Alignment.Center),
                            fontWeight = FontWeight.W600,
                            fontSize = 9.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.4.sp,
                            color = Color.White,
                        )
                    }
                }

                Box(
                    Modifier
                        .width(50.dp)
                        .background(Color(0xFFA0B710), RoundedCornerShape(4.dp))
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                ) {
                    item.tags.getOrNull(2)?.let {
                        Text(
                            text = it.tagName,
                            modifier = Modifier.align(Alignment.Center),
                            fontWeight = FontWeight.W600,
                            fontSize = 9.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.4.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
        Column(
            Modifier
                .padding(start = 50.dp, bottom = 3.dp)
                .height(135.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { isPressed = !isPressed },
            content = {
                if(isPressed) Icon(painter = painterResource(id = R.drawable.star_full), contentDescription = "", tint = Color.Yellow) else Icon(painter = painterResource(id = R.drawable.star), contentDescription = "", tint = Color.Yellow)
            })

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = item.company.companyName,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.Green,
                )
        }
    }
}