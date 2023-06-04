package com.example.employmenow.Repository

import com.example.employmenow.API.Api
import com.example.employmenow.Models.JobModel
import retrofit2.Response

class JobsRepository(private val api: Api) {

    suspend fun getAllJobs(): Response<List<JobModel>> {
        return api.getAllJobs()
    }

    suspend fun getJobById(jobId: String): Response<JobModel> {
        return api.getJob(jobId)
    }

    suspend fun getFavoriteJobs(jwtToken: String): Response<List<JobModel>> {
        return api.getFavoriteJobs("Bearer $jwtToken")
    }
}