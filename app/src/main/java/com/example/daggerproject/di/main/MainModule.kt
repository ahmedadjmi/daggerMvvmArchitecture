package com.example.daggerproject.di.main

import com.example.daggerproject.network.main.MainApi
import com.example.daggerproject.ui.main.post.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    companion object {
        @MainScope
        @Provides
        fun provideMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }
        @MainScope
        @Provides
        fun provideRecyclerAdapter(): PostsRecyclerAdapter {
            return PostsRecyclerAdapter()
        }

    }

}