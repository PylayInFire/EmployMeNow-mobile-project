package com.example.employmenow.API

import com.example.employmenow.Models.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @POST("/api/auth/workerGoogleReg")
    suspend fun generateJWT(@Body request: WorkerRegistrationRequest): Response<WorkerRegistrationResponse>

    @POST("/api/auth/googleLogin")
    suspend fun getJWT(@Query("email") email: String): Response<WorkerLoginResponse>

    @GET("/api/jobs/get/{id}")
    suspend fun getJob(@Path("id") id: String): Response<JobModel>

    @GET("/api/jobs/get")
    suspend fun getAllJobs(): Response<List<JobModel>>


    @GET("/api/workers/get/{email}")
    suspend fun getWorker(@Path("email") email: String): Response<WorkerModel>

    @POST("/api/workerAction/addToFavorite")
    suspend fun addToFavorite(
        @Query("jobId") jobId: String,
        @Header("Authorization") jwtToken: String
    ): Response<Unit>

    @POST("/api/workerAction/removeFromFavorite")
    suspend fun removeFromFavorite(
        @Query("jobId") jobId: String,
        @Header("Authorization") jwtToken: String
    ): Response<Unit>

    @POST("/api/workerAction/getFavoriteJobs")
    suspend fun getFavoriteJobs(
        @Header("Authorization") jwtToken: String
    ): Response<List<JobModel>>

    @POST("/api/workerAction/setCV")
    @Multipart
    suspend fun uploadCV(
        @Part formFile: MultipartBody.Part,
        @Header("Authorization") jwtToken: String
    ): Response<Unit>

    @POST("/api/workerAction/setAvatar")
    @Multipart
    suspend fun setAvatar(
        @Part formFile: MultipartBody.Part,
        @Header("Authorization") jwtToken: String
    ): Response<Unit>

    @POST("/api/workerAction/giveFeadback")
    suspend fun giveFeedback(
        @Header("Authorization") jwtToken: String,
        @Body feedbackRequest: FeedbackRequest
    ): Response<Unit>


    @POST("/api/workerAction/getWorkerFeadbacks")
    suspend fun getUsersFeedbacks(
        @Header("Authorization") jwtToken: String
    ): Response<List<Feedback>>

}