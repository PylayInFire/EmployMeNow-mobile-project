package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class JobModel(
    @SerializedName("jobId")
    val jobId: String,

    @SerializedName("jobName")
    val jobName: String,

    @SerializedName("tags")
    val tags: List<Tag>,

    @SerializedName("description")
    val description: String,

    @SerializedName("company")
    val company: Company,

    @SerializedName("feadbacks")
    val feedbacks: List<Feedback>,

    var isFavorite: Boolean = false,

    )