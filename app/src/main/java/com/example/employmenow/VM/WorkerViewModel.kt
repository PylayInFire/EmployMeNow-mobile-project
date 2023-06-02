package com.example.employmenow.VM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employmenow.API.ApiService
import com.example.employmenow.Repository.WorkerRepository
import kotlinx.coroutines.launch

class WorkerViewModel: ViewModel() {

    private val _hasCv = MutableLiveData<Boolean>()
    val hasCv: LiveData<Boolean> = _hasCv

    private val _avatar = MutableLiveData<String?>()
    val avatar: LiveData<String?> = _avatar

    private val _isBanned = MutableLiveData<Boolean>()
    val isBanned: LiveData<Boolean> = _isBanned

    private val _savedEmail = MutableLiveData<String>()
    val savedEmail: MutableLiveData<String> = _savedEmail

    fun getUserDataByEmail(email: String) {
        viewModelScope.launch {
            try {
                val api = ApiService.api
                val repository = WorkerRepository(api)
                val response = repository.getWorker(email)
                if (response.isSuccessful) {
                    val worker = response.body()
                    if (worker != null) {
                        _hasCv.value = worker.hasCv
                        _avatar.value = worker.avatar
                        _isBanned.value = worker.isBanned
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun savedEmail(email: String) {
        _savedEmail.value = email
    }

    fun giveFeedback(jobId: String, workerComment: String) {
        viewModelScope.launch {
            try {
                val api = ApiService.api
                val jwtToken = ApiService.getUserPreference().getString("jwt", "")
                val repository = WorkerRepository(api)
                if (jwtToken != null) {
                   val a = repository.giveFeedback(jwtToken, jobId, workerComment)
                   println(a)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}