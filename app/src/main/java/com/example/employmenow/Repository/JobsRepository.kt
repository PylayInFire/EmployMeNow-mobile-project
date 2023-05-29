package com.example.employmenow.Repository

import com.example.employmenow.API.Api
import com.example.employmenow.Models.JobModel
import retrofit2.Response

class JobsRepository(private val api: Api) {

    suspend fun getAllJobs(): Response<List<JobModel>> {
        return api.getAllJobs()
    }
}