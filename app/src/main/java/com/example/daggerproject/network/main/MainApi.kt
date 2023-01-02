package com.example.daggerproject.network.main

import com.example.daggerproject.models.Post
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts")
    fun getPostFromUser(@Query("userId") id: Int): Flowable<List<Post>>
}