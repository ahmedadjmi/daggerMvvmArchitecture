package com.example.daggerproject.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.daggerproject.R
import com.example.daggerproject.models.User
import com.example.daggerproject.ui.viewmodel.ViewModelProviderFactory
import com.example.daggerproject.utils.AuthStatus
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    private val TAG = "ProfileFragment"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: ProfileViewModel
    private lateinit var userName: TextView
    private lateinit var email: TextView
    private lateinit var webSite: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "Profile Framgment is shown", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragement_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userName = view.findViewById(R.id.username)
        email = view.findViewById(R.id.email)
        webSite = view.findViewById(R.id.website)


        viewModel = ViewModelProvider(this, providerFactory)[ProfileViewModel::class.java]
        subscireObserves()
    }

    private fun subscireObserves() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }
                    AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                    AuthStatus.LOADING -> TODO()
                    AuthStatus.NOT_AUTHENTICATED -> TODO()
                }
            }

        }
    }

    private fun setErrorDetails(message: String?) {
        message?.let {
            email.text = it
            userName.text = "Error"
            webSite.text = "Error"
        }
    }

    private fun setUserDetails(data: User?) {
        data?.let {
            email.text = it.email
            userName.text = it.userName
            webSite.text = it.webSite
        }
    }
}