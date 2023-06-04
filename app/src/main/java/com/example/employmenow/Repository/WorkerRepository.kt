package com.example.employmenow.Repository

import com.example.employmenow.API.Api
import com.example.employmenow.Models.Feedback
import com.example.employmenow.Models.FeedbackRequest
import com.example.employmenow.Models.WorkerModel
import okhttp3.ResponseBody
import retrofit2.Response

class WorkerRepository(private val api: Api) {

    suspend fun getWorker(email: String): Response<WorkerModel> {
        return api.getWorker(email)
    }

    suspend fun giveFeedback(jwtToken: String, jobId: String, workerComment: String): Response<Unit> {
        val feedbackModel: FeedbackRequest = FeedbackRequest(jobId.toInt(), workerComment)
        return api.giveFeedback("Bearer $jwtToken",feedbackModel)
    }

    suspend fun removeFromFavorite(jobId: String, jwtToken: String): Response<Unit> {
        return api.removeFromFavorite(jobId, "Bearer $jwtToken")
    }

    suspend fun addToFavorite(jobId: String, jwtToken: String): Response<Unit> {
        return api.addToFavorite(jobId, "Bearer $jwtToken")
    }

    suspend fun getWorkerFeebacks(jwtToken: String): Response<List<Feedback>> {
        return api.getUsersFeedbacks("Bearer $jwtToken")
    }

}