package com.example.daggerproject.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggerproject.SessionManager
import com.example.daggerproject.models.User
import com.example.daggerproject.utils.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    private val TAG = "ProfileViewModel"

    @Inject
    lateinit var sessionManager: SessionManager

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}