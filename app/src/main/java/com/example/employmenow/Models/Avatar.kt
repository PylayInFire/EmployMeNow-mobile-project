package com.example.employmenow.Models

data class Avatar(
    @SerializedName("id")
    val id: Int,
    @SerializedName("content")
    val content: String
)