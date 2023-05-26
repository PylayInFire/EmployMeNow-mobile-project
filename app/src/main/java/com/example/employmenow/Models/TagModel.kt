package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("tagId")
    val tagId: Int,

    @SerializedName("tagName")
    val tagName: String,

    @SerializedName("jobs")
    val jobs: List<JobModel>?
)