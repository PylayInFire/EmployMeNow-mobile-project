package com.example.employmenow.Repository

import com.example.employmenow.API.Api
import okhttp3.MultipartBody
import retrofit2.Response

class FileUploadRepository(private val api: Api, private val jwtToken: String) {


    suspend fun uploadPdf(part: MultipartBody.Part): Response<Unit> {
        return api.uploadCV(part, "Bearer $jwtToken")
    }

    suspend fun uploadImg(part: MultipartBody.Part): Response<Unit> {
        return api.setAvatar(part, "Bearer $jwtToken")
    }
}