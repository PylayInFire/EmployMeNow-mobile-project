package com.example.employmenow.AppUI.screens

import android.graphics.pdf.PdfDocument.Page
import com.example.employmenow.R

sealed class Pages(
    val image: Int,
    val slogan: String

) {
    object First: Pages(
        image = R.drawable.page1,
        slogan = "Unleash Your True Potential"
    )
    object Second: Pages(
        image = R.drawable.page2,
        slogan = "Trusted by Thousands"
    )
    object Third: Pages(
        image = R.drawable.page3,
        slogan = "Start Your Job Search Now"
    )
}