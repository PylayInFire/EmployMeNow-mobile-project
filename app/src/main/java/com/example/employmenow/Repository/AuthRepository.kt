package com.example.employmenow.Repository

import android.content.SharedPreferences
import com.example.employmenow.API.Api
import com.example.employmenow.Models.WorkerRegistrationRequest
import retrofit2.HttpException
import java.io.IOException

class AuthRepository(private val api: Api, private val sharedPreferences: SharedPreferences) {

    suspend fun googleReg(model: WorkerRegistrationRequest): Boolean {
        try {
            val response = api.generateJWT(model)
            if (response.isSuccessful) {
                val jwt = response.body()?.jwtToken
                if (jwt != null) {
                    localSaveToken(jwt)
                    return true
                } else {
                    val emailErrors = response.body()?.errors?.get("email")
                    if (!emailErrors.isNullOrEmpty()) {
                        val errorMessage = emailErrors[0]
                        if(errorMessage == "this email is already taken") {
                            return login(model.email)
                        }
                    }
                }
            } else {

                println("Ошибка: ${response.code()}")
                return false
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return false
        } catch (ex: HttpException) {
            ex.printStackTrace()
            return false
        }
        return false
    }

    suspend fun login(email: String): Boolean {
        try {
            val response = api.getJWT(email)
            if(response.isSuccessful) {
                val jwt = response.body()?.jwtToken
                if (jwt != null) {
                    localSaveToken(jwt)
                    return true
                } else {
                    return false
                }
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }

    private fun localSaveToken(jwt: String) {
        sharedPreferences.edit().putString("jwt", jwt).apply()
    }

}