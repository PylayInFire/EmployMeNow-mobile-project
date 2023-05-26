package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName

data class WorkerRegistrationResponse(
    @SerializedName("successed")
    val successed: Boolean,
    @SerializedName("jwtToken")
    val jwtToken: String,
    @SerializedName("errors")
    val errors: Map<String, List<String>>
)