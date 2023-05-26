package com.example.employmenow.VM

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employmenow.API.ApiService
import com.example.employmenow.Models.WorkerRegistrationRequest
import com.example.employmenow.Repository.AuthRepository
import com.example.employmenow.VM.Status.LoadingStatus
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel(){

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus
    private val _googleAccount = MutableLiveData<GoogleSignInAccount>()
    val googleAccount: LiveData<GoogleSignInAccount> = _googleAccount

    fun performSignUp(data: Intent) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.Loading
            val task = GoogleSignIn.getSignedInAccountFromIntent(data) // Возвращаем асинхронную задачу (Task<GoogleSignInAccount>)
            try {
                val account = task.getResult(ApiException::class.java) //Вернём объект типа GoogleSignInAccount, после чего достаём нужную для нас инфу
                val email = account?.email ?: "" //избегаем null
                val name = account?.givenName ?: ""
                val surname = account?.familyName ?: ""
                Log.d("TAG", "Email: $email")
                Log.d("TAG", "Name: $name")
                Log.d("TAG", "Surname: $surname")
                val resume = "blablabla"
                val model = WorkerRegistrationRequest(email, name, surname, resume, 1)
                val api = ApiService.api
                val sharedPreferences = ApiService.getUserPreference()
                val repository = AuthRepository(api, sharedPreferences)
                if(repository.googleReg(model)) {
                    _loadingStatus.value = LoadingStatus.Success
                    _googleAccount.value = account
                } else {
                    _loadingStatus.value = LoadingStatus.Error
                }
            } catch (ex: ApiException) {
                ex.printStackTrace()
                _loadingStatus.value = LoadingStatus.Error
            }
        }
    }
}