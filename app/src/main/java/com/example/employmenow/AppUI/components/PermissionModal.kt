package com.example.employmenow.AppUI.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import com.example.employmenow.R

@Composable
fun PermissionModal(
    requestPermissions: () -> Unit,
    onPermissionGranted: () -> Unit,
) {
    var isDialogVisible by remember { mutableStateOf(false) }
         LaunchedEffect(Unit) {
             if (!isDialogVisible) {
                 delay(1000)
                 isDialogVisible = true
            }
        }
        AnimatedVisibility(visible = isDialogVisible) {
            AlertDialog(
                onDismissRequest = {},
                buttons = {
                    TextButton(
                        {
                            requestPermissions()
                            onPermissionGranted()
                        },
                        Modifier
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                            .fillMaxWidth()
                            .background(Color(0xFF3CF283), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp),
                    ) {
                        Text(
                            "Allow",
                            color = Color.Black,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                title = {
                    Text(
                        text = "For future using you need to allow these",
                        fontWeight = FontWeight.W600,
                        fontSize = 14.sp,
                        lineHeight = 28.sp,
                        letterSpacing = 0.4.sp,
                        color = Color(0xFFFFFFFF)
                    )
                },
                backgroundColor = Color(0xFF373636),
                text = {
                    Column {
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.micro),
                                "fsdfs",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.padding(start = 16.dp))
                            Text(
                                text = "Recording audio",
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 24.sp,
                                letterSpacing = 0.4.sp,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.directory),
                                "sdfsd",
                                modifier = Modifier.size(15.dp, 15.dp),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.padding(start = 16.dp))
                            Text(
                                text = "Storage Reading",
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp,
                                lineHeight = 24.sp,
                                letterSpacing = 0.4.sp,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                    }
                }
            )
        }

}