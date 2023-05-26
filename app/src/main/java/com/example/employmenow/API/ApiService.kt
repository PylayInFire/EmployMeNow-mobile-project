package com.example.employmenow.API

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor

object ApiService {

    private var authPreferences: SharedPreferences? = null
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // интерцептор для логирования запросов (для теста)
        .build()

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl("http://pylay1-001-site1.gtempurl.com/") //если сменим хост то здесь надо будет поменять
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }

    fun createPref(context: Context) {
        authPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    fun getUserPreference(): SharedPreferences {
        return authPreferences ?: throw IllegalStateException("Настройки не были инициализированы")
    }

}