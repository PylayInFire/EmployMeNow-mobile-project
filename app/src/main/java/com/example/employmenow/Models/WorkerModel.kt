package com.example.employmenow.Models

import com.google.gson.annotations.SerializedName


data class WorkerModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("resumeInfo")
    val resumeInfo: String,
    @SerializedName("tagId")
    val tagId: Int,
    @SerializedName("tag")
    val tag: Tag,
    @SerializedName("avatar")
    val avatar: Avatar?,
    @SerializedName("cv")
    val cv: String?,
    @SerializedName("hasCv")
    val hasCv: Boolean,
    @SerializedName("feadbacks")
    val feadbacks: List<Feedback>,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String?,
    @SerializedName("role")
    val role: String,
    @SerializedName("isBanned")
    val isBanned: Boolean
)