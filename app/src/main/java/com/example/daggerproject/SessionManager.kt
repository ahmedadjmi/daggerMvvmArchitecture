package com.example.daggerproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.daggerproject.models.User
import com.example.daggerproject.utils.AuthResource
import java.util.Objects
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    val cashedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()
    fun authenticatedWithId(source: LiveData<AuthResource<User>>) {
        if (Objects.nonNull(cashedUser)) {
            cashedUser.value = AuthResource.loading()
            cashedUser.addSource(source) {
                cashedUser.value = it
                cashedUser.removeSource(source)
            }
        } else {
            Log.d("SessionManager", "previous session detected. Retrieving user from cashe.")
        }
    }

    fun logout() {
        Log.d("SessionManager", "Logout....")
        cashedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cashedUser
    }

}