package com.example.employmenow.VM.Status

sealed class LoadingStatus {
    object Loading : LoadingStatus()
    object Success : LoadingStatus()
    object Error: LoadingStatus()
}