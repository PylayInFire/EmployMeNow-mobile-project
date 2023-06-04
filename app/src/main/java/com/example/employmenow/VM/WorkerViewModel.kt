package com.example.employmenow.VM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.employmenow.API.ApiService
import com.example.employmenow.Models.Feedback
import com.example.employmenow.Models.JobModel
import com.example.employmenow.Repository.WorkerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkerViewModel: ViewModel() {

    private val _hasCv = MutableLiveData<Boolean>()
    val hasCv: LiveData<Boolean> = _hasCv

    private val _avatar = MutableLiveData<String>()
    val avatar: LiveData<String> = _avatar

    private val _isBanned = MutableLiveData<Boolean>()
    val isBanned: LiveData<Boolean> = _isBanned

    private val _savedEmail = MutableLiveData<String>()
    val savedEmail: MutableLiveData<String> = _savedEmail

    private val _feedbackCount = MutableLiveData<Int>()
    val feedbackCount: MutableLiveData<Int> = _feedbackCount

    private val _acceptedJobs = MutableLiveData<List<Feedback>>()
    val acceptedJobs: LiveData<List<Feedback>> = _acceptedJobs

    private val _declinedJobs = MutableLiveData<List<Feedback>>()
    val declinedJobs: LiveData<List<Feedback>> = _declinedJobs

    fun getUserDataByEmail(email: String) {
        viewModelScope.launch {
            try {
                val api = ApiService.api
                val repository = WorkerRepository(api)
                val response = repository.getWorker(email)
                val a = response.code()
                if (response.isSuccessful) {
                    val worker = response.body()
                    if (worker != null) {
                        _hasCv.value = worker.hasCv
                        _avatar.value = worker?.avatar?.content
                        _isBanned.value = worker.isBanned
                    }
                } else {
                    val errorBody = response.errorBody()
                    Log.e("API", "Error response: ${errorBody?.string()}")
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
                   repository.giveFeedback(jwtToken, jobId, workerComment)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun addToFavorite(jobId: String) {
        viewModelScope.launch {
            try {
                val api = ApiService.api
                val jwtToken = ApiService.getUserPreference().getString("jwt", "")
                val repository = WorkerRepository(api)
                if(jwtToken != null) {
                    repository.addToFavorite(jobId, jwtToken)
                    repository.getWorkerFeebacks(jwtToken) //Тест
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
    fun removeFromFavorite(jobId: String) {
        viewModelScope.launch {
            try {
                val api = ApiService.api
                val jwtToken = ApiService.getUserPreference().getString("jwt", "")
                val repository = WorkerRepository(api)
                if(jwtToken != null) {
                    repository.removeFromFavorite(jobId, jwtToken)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun getFeedbackCounts() {
        viewModelScope.launch {
            try {
                val api = ApiService.api
                val jwtToken = ApiService.getUserPreference().getString("jwt", "")
                val repository = WorkerRepository(api)
                if (jwtToken != null) {
                    val feedbackList = repository.getWorkerFeebacks(jwtToken)
                    if (feedbackList.isSuccessful) {
                        val count = feedbackList.body()?.count { it.accepted != null } ?: 0
                        _feedbackCount.value = count
                    } else {
                        _feedbackCount.value = 0
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun getFeedbackJobs() {
        viewModelScope.launch {
        try {
            val api = ApiService.api
            val jwtToken = ApiService.getUserPreference().getString("jwt", "")
            val repository = WorkerRepository(api)
            if (jwtToken != null) {
                val feedbackList = repository.getWorkerFeebacks(jwtToken)
                if (feedbackList.isSuccessful) {
                    val feedbacks = feedbackList.body()
                    if(feedbacks != null) {
                        val accepted = feedbacks.filter { it.accepted == true }
                        val declined = feedbacks.filter { it.accepted == false }
                        _acceptedJobs.value = accepted
                        _declinedJobs.value = declined
                    }
                } else {
                    _feedbackCount.value = 0
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        }
    }
}