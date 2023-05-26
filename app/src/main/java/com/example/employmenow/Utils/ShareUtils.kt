package com.example.employmenow.Utils

import android.content.Context
import android.content.Intent

object ShareUtils {
    fun shareText(context: Context, text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)

        val chooserIntent = Intent.createChooser(shareIntent, "Поделиться")

        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(chooserIntent)
        }
    }
}