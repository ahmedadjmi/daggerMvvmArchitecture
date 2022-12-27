package com.example.daggerproject.utils

data class AuthResource<out T>(val status: AuthStatus, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String): AuthResource<T> {
            return AuthResource(AuthStatus.ERROR, null, msg)
        }

        fun <T> loading(): AuthResource<T> {
            return AuthResource(AuthStatus.LOADING, null, null)
        }
        fun <T> logout(): AuthResource<T> {
            return AuthResource(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }
}