package com.example.employmenow.Models

data class WorkerLoginResponse(
    val successed: Boolean,
    val jwtToken: String,
    val errors: Map<String, List<String>>
)