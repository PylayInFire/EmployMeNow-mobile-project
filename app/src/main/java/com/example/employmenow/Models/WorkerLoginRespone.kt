package com.example.employmenow.Models

data class WorkerLoginResponse(
    @SerializedName("successed")
    val successed: Boolean,
    @SerializedName("jwtToken")
    val jwtToken: String,
    @SerializedName("errors")
    val errors: Map<String, List<String>>
)