package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("companyName")
    val companyName: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("jobs")
    val jobs: List<JobModel>,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("role")
    val role: String,

    @SerializedName("isBanned")
    val isBanned: Boolean
)