package com.example.employmenow.Repository

import com.example.employmenow.API.Api
import com.example.employmenow.Models.Feedback
import com.example.employmenow.Models.WorkerModel
import retrofit2.Response

class WorkerRepository(private val api: Api) {

    suspend fun getWorker(email: String): Response<WorkerModel> {
        return api.getWorker(email)
    }

    suspend fun giveFeedback(jwtToken: String, jobId: String, workerComment: String): Response<Unit> {
        val feedbackModel: Feedback = Feedback(jobId.toInt(), workerComment)
        return api.giveFeedback("Bearer $jwtToken",feedbackModel)
    }

}