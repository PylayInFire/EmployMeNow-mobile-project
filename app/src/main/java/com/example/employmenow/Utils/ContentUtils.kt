package com.example.employmenow.Utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import java.io.File
import java.io.InputStream
import java.util.Locale

object ContentUtils {
    fun getInputStream(context: Context, uri: Uri): InputStream? {
        return context.contentResolver.openInputStream(uri)
    }

    fun getLength(context: Context, uri: Uri): Long {
        return context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            if (cursor.moveToFirst()) {
                cursor.getLong(sizeIndex)
            } else {
                0L
            }
        } ?: 0L
    }

    fun getType(context: Context, uri: Uri): String? {
        return when (uri.scheme) {
            ContentResolver.SCHEME_CONTENT -> context.contentResolver.getType(uri)
            ContentResolver.SCHEME_FILE -> MimeTypeMap.getSingleton().getMimeTypeFromExtension(
                MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase(Locale.US)
            )
            else -> null
        }
    }

    fun getFileName(context: Context, uri: Uri): String? {
        return when (uri.scheme) {
            ContentResolver.SCHEME_FILE -> {
                val filePath = uri.path
                if (!filePath.isNullOrEmpty()) {
                    File(filePath).name
                } else {
                    null
                }
            }
            ContentResolver.SCHEME_CONTENT -> {
                getCursorContent(uri, context.contentResolver)
            }
            else -> null
        }
    }

    private fun getCursorContent(uri: Uri, contentResolver: ContentResolver): String? {
        return kotlin.runCatching {
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val nameColumnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (cursor.moveToFirst()) {
                    cursor.getString(nameColumnIndex)
                } else {
                    null
                }
            }
        }.getOrNull()
    }

}