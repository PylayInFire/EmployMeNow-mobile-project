package com.example.employmenow.Utils

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContract
import java.util.Locale

class VoiceRecognition: ActivityResultContract<Int, String>() {

    override fun createIntent(context: Context, input: Int): Intent {
        val recognitionIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak name of vacancies to search for")
        }
        return recognitionIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        val text = intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) ?: ""
        return text
    }

}