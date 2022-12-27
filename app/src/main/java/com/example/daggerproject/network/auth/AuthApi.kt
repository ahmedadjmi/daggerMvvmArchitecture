package com.example.daggerproject.network.auth

import com.example.daggerproject.models.User
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Flowable<User>
}