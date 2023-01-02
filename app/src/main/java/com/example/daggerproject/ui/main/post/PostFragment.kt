package com.example.daggerproject.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerproject.R
import com.example.daggerproject.ui.viewmodel.ViewModelProviderFactory
import com.example.daggerproject.utils.Resource
import com.example.daggerproject.utils.VerticalSpacingItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostFragment : DaggerFragment() {
    private val TAG = "PostFragment"

    private lateinit var postViewModel: PostViewModel
    private lateinit var postRecycleView: RecyclerView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postRecycleView = view.findViewById(R.id.recycler_view)
        postViewModel = ViewModelProvider(this, providerFactory)[PostViewModel::class.java]
        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        postViewModel.observePost().removeObservers(viewLifecycleOwner)
        postViewModel.observePost().observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d("aaaa", "loading")
                }
                Resource.Status.ERROR -> {
                    Log.d("aaaa", "Error")
                }
                Resource.Status.SUCCESS -> {
                    Log.d("aaaa", "sucess")
                    it.data?.let {
                        postsRecyclerAdapter.setPosts(it)
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        postRecycleView.layoutManager = LinearLayoutManager(activity)
        postRecycleView.addItemDecoration(VerticalSpacingItemDecoration(15))
        postRecycleView.adapter = postsRecyclerAdapter
    }
}