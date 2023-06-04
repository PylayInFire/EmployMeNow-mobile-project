package com.example.employmenow.VM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employmenow.API.ApiService
import com.example.employmenow.Models.JobModel
import com.example.employmenow.Repository.JobsRepository
import com.example.employmenow.VM.Status.LoadingStatus
import kotlinx.coroutines.launch

class JobViewModel: ViewModel() {

    private val _jobs = MutableLiveData<List<JobModel>>()
    val jobs: LiveData<List<JobModel>> = _jobs

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    private val _favoriteJobs = MutableLiveData<List<JobModel>>()
    var favoriteJobs: LiveData<List<JobModel>> = _favoriteJobs

    private val _selectedJobById = MutableLiveData<JobModel>()
    val selectedJobById: LiveData<JobModel> = _selectedJobById

    fun getJobs() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            val api = ApiService.api
            val jwtToken = ApiService.getUserPreference().getString("jwt", "")
            val repository = JobsRepository(api)
            try {
                val jobsResponse = repository.getAllJobs()
                val favoriteJobsResponse = jwtToken?.let { repository.getFavoriteJobs(it) }
                if (jobsResponse.isSuccessful) {
                    val jobs = jobsResponse.body()
                    val favoriteJobs = favoriteJobsResponse?.body()

                    val allJobs = jobs?.map { job ->
                        val isFavorite = favoriteJobs?.any { favoriteJob -> favoriteJob.jobId == job.jobId } == true
                        job.copy(isFavorite = isFavorite)
                    }
                    _favoriteJobs.value = favoriteJobs
                    _jobs.value = allJobs
                    _loadingStatus.value = LoadingStatus.Success
                } else {
                    _loadingStatus.value = LoadingStatus.Error
                }
            } catch (e: Exception) {
                _loadingStatus.value = LoadingStatus.Error
            }
        }
    }

    fun getJobById(jobId: String) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            val api = ApiService.api
            val repository = JobsRepository(api)
            val response = repository.getJobById(jobId)
            if(response.isSuccessful) {
                val job = response.body()
                _selectedJobById.value = job
                _loadingStatus.value = LoadingStatus.Success
            } else {
                _loadingStatus.value = LoadingStatus.Error
            }
        }
    }

    fun updateFavoriteJobs() {
        val updatedFavoriteJobs = jobs.value?.filter { it.isFavorite } ?: emptyList()
        _favoriteJobs.value = updatedFavoriteJobs
    }
}