package com.example.employmenow.VM

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class SharedGoogleViewModel: ViewModel() {

    private val _googleAccount = MutableLiveData<GoogleSignInAccount?>()
    val googleAccount: LiveData<GoogleSignInAccount?> get() = _googleAccount

    fun setGoogleAccount(account: GoogleSignInAccount) {
        _googleAccount.value = account
    }

}