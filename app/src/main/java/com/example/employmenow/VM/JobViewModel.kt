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

    fun getJobs() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            val api = ApiService.api
            val repository = JobsRepository(api)
            val response = repository.getAllJobs()
            if (response.isSuccessful) {
                val jobs = response.body()
                _jobs.value = jobs
                _loadingStatus.value = LoadingStatus.Success
            } else {
                _loadingStatus.value = LoadingStatus.Error
            }
                _loadingStatus.value = LoadingStatus.Error
        }
    }
}