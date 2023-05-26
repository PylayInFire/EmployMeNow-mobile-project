package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class Feedback(
    @SerializedName("jobFeadbackId")
    val jobFeedbackId: Int,
    @SerializedName("workerComment")
    val workerComment: String,
    @SerializedName("job")
    val job: JobModel?,
    @SerializedName("accepted")
    val accepted: Boolean?,
    @SerializedName("worker")
    val worker: WorkerModel?
)