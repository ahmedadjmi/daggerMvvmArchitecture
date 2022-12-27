package com.example.daggerproject.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.daggerproject.SessionManager
import com.example.daggerproject.models.User
import com.example.daggerproject.network.auth.AuthApi
import com.example.daggerproject.utils.AuthResource
import com.example.daggerproject.utils.AuthStatus
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(
    private val authApi: AuthApi, private val sessionManager: SessionManager
) : ViewModel() {
    fun authenticateWithId(id: Int) {
        Log.d("AuthViewModel", "Authentication with id started id= $id")
        sessionManager.authenticatedWithId(
            queryUserId(id)
        )
    }

    private fun queryUserId(id: Int): LiveData<AuthResource<User>> =
        LiveDataReactiveStreams.fromPublisher(authApi.getUserById(id).onErrorReturn {
            User(id = -1, "", "", "")
//                        Resource(Status.ERROR, null, "user not found")
        }.map {
            if (it.id == -1) {
                return@map AuthResource.error("Error user not found")
            }
            AuthResource.success(it)
        }.subscribeOn(Schedulers.io())
        )

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.cashedUser
    }
}