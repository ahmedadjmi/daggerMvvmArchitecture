package com.example.daggerproject.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.daggerproject.R
import com.example.daggerproject.ui.main.MainActivity
import com.example.daggerproject.ui.viewmodel.ViewModelProviderFactory
import com.example.daggerproject.utils.AuthStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: AuthViewModel
    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager
    private lateinit var userId: EditText
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
        subscibeObservers()

        userId = findViewById(R.id.user_id_input)
        loginButton = findViewById(R.id.login_button)
        progressBar = findViewById(R.id.progress_bar)
        loginButton.setOnClickListener {
            if (!TextUtils.isEmpty(userId.text.toString())) {
                viewModel.authenticateWithId(Integer.parseInt(userId.text.toString()))
            }
        }
    }

    private fun subscibeObservers() {
        viewModel.observeAuthState().observe(this) {
            it?.let {
                when (it.status) {
                    AuthStatus.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    AuthStatus.AUTHENTICATED -> {
                        progressBar.visibility = View.GONE
                        Log.d("login success", "email: " + it.data?.email)
                        onLoginSucces()
                    }
                    AuthStatus.ERROR -> {
                        progressBar.visibility = View.GONE
                        Log.d("login failed", "msg: " + it.message)
                        Toast.makeText(this, "Error trying to login", Toast.LENGTH_LONG).show()
                    }
                    AuthStatus.NOT_AUTHENTICATED -> {
                        progressBar.visibility = View.GONE
                        Log.d("logedout...", "")
                    }
                }
            }
        }
    }

    private fun setLogo() {
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }

    private fun onLoginSucces() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}