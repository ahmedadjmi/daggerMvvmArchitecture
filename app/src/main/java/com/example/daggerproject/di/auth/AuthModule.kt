package com.example.daggerproject.di.auth

import com.example.daggerproject.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    companion object {
        @AuthScope
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }
}