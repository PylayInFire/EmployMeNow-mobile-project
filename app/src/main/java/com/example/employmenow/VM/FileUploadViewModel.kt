package com.example.employmenow.VM

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employmenow.API.ApiService
import com.example.employmenow.API.InputStreamRequestBody
import com.example.employmenow.Repository.FileUploadRepository
import com.example.employmenow.Utils.ContentUtils
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class FileUploadViewModel: ViewModel() {

    private val _uploadProgress = MutableLiveData<Int>()
    val uploadProgress: LiveData<Int> = _uploadProgress

    fun uploadPdf(uri: Uri, context: Context) {
        viewModelScope.launch {
            val fileName = ContentUtils.getFileName(context, uri)
            val contentType = ContentUtils.getType(context, uri)
            val requestBody = InputStreamRequestBody(context, uri) { progress ->
                _uploadProgress.postValue(progress)
            }
            val api = ApiService.api
            val jwtToken = ApiService.getUserPreference().getString("jwt", "")
            val repository = jwtToken?.let { FileUploadRepository(api, it) }
            val part = MultipartBody.Part.createFormData("formFile", fileName, requestBody)
            repository?.uploadPdf(part)
        }
    }
}