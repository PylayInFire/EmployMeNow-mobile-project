package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class FeedbackRequest(
    val jobId: Int,
    val workerComment: String
)