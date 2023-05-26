package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class FeedbackRequest(
    @SerializedName("jobId")
    val jobId: Int,
    @SerializedName("workerComment")
    val workerComment: String
)