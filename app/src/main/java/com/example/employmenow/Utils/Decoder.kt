package com.example.employmenow.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

class Decoder {
    companion object {
        fun decodeToBitmap(base64: String): Bitmap? {
            val bytes =  Base64.decode(base64, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    }
}