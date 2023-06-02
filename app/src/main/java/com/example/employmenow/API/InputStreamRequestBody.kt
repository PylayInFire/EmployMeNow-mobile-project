package com.example.employmenow.API

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.example.employmenow.Utils.ContentUtils
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File

class InputStreamRequestBody(
    private val context: Context,
    private val uri: Uri,
    private val progressChanged: (Int) -> Unit
) : RequestBody() {

    override fun contentType(): MediaType? {
        return ContentUtils.getType(context, uri)?.toMediaTypeOrNull()
    }

    override fun contentLength(): Long {
        return ContentUtils.getLength(context, uri)
    }

    override fun writeTo(sink: BufferedSink) {
        ContentUtils.getInputStream(context, uri)?.use { inputStream ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var totalBytesRead: Long = 0
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                sink.write(buffer, 0, bytesRead)
                totalBytesRead += bytesRead
                val progress = (totalBytesRead.toFloat() / contentLength() * 100).toInt()
                progressChanged(progress)
            }
        }
    }

}