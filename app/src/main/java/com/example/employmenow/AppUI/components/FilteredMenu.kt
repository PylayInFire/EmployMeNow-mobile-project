package com.example.employmenow.AppUI.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.employmenow.Models.JobModel
import com.example.employmenow.R


@Composable
fun FilteredMenu(jobs: List<JobModel>, onSortedPerformed: (List<JobModel>?) -> Unit) {
    val options = listOf("A-Z", "Z-A", "Reset")
    var isOpen by remember { mutableStateOf(false); }
    val onOptionSelected: (String) -> Unit = { option ->
        when (option) {
            "A-Z" -> {
                val jobsAsc = jobs.sortedBy { it.jobName.trim() }
                onSortedPerformed(jobsAsc)
            }
            "Z-A" -> {
                val jobsDesc = jobs.sortedByDescending { it.jobName.trim() }
                onSortedPerformed(jobsDesc)
            }
            "Reset" -> {
                onSortedPerformed(jobs)
            }
        }
        isOpen = false
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color(0xFF272727))
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF272727)),
            onClick = { isOpen = !isOpen },
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(
                modifier = Modifier.padding(end = 9.dp),
                text = "Sort By",
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp,
                color = Color.White
            )
            Icon(
                painter = painterResource(id = R.drawable.arraydropdown),
                contentDescription = "dropdown",
                tint = Color(0xFF777777)
            )
        }
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = { isOpen = false },
            modifier = Modifier.background(Color(0xFF272727))
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(onClick = { onOptionSelected(option) }) {
                    Text(
                        text = option,
                        style = TextStyle(
                            fontWeight = FontWeight.W400,
                            fontSize = 20.sp,
                            lineHeight = 16.sp,
                            letterSpacing = 0.4.sp,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}