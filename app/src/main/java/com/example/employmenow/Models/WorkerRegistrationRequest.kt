package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class WorkerRegistrationRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("resumeInfo")
    val resumeInfo: String,
    @SerializedName("tagId")
    val tagId: Int
)