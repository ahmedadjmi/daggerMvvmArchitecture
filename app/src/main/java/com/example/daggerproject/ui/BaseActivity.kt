package com.example.daggerproject.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.example.daggerproject.SessionManager
import com.example.daggerproject.ui.auth.AuthActivity
import com.example.daggerproject.utils.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
     lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscibeObservers()
    }

    private fun subscibeObservers() {
        sessionManager.getAuthUser().observe(this) {
            it?.let {
                when (it.status) {
                    AuthStatus.LOADING -> {
                    }
                    AuthStatus.AUTHENTICATED -> {
                        Log.d("login success", "email: " + it.data?.email)
                    }
                    AuthStatus.ERROR -> {
                        Log.d("login failed", "msg: " + it.message)
                    }
                    AuthStatus.NOT_AUTHENTICATED -> {
                        Log.d("logedout...", "")
                        navToLoginScreen()
                    }
                }
            }
        }
    }

    private fun navToLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}