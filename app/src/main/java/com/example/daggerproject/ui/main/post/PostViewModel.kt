package com.example.daggerproject.ui.main.post

import androidx.lifecycle.*
import com.example.daggerproject.SessionManager
import com.example.daggerproject.models.Post
import com.example.daggerproject.network.main.MainApi
import com.example.daggerproject.utils.AuthResource
import com.example.daggerproject.utils.Resource
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.ObjectStreamClass
import java.util.*
import javax.inject.Inject

class PostViewModel @Inject constructor(var sessionManager: SessionManager, var mainApi: MainApi) :
    ViewModel() {
    private val posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()

    fun observePost(): LiveData<Resource<List<Post>>> {
        if (Objects.nonNull(posts)) {
            posts.value = Resource.loading()
            val source =
                LiveDataReactiveStreams.fromPublisher(mainApi.getPostFromUser(sessionManager.getAuthUser().value!!.data!!.id)
                    .subscribeOn(Schedulers.io()).onErrorReturn {
                        return@onErrorReturn listOf(Post(-1, 0, "", ""))
                    }).map {
                    if (it.isNotEmpty()) {
                        if (it[0].id == -1) {
                            return@map Resource(Resource.Status.ERROR, null, "Somthing went wrong")
                        }
                    }
                    return@map Resource(Resource.Status.SUCCESS, it, null)
                }
            posts.addSource(source) {
                it?.let {
                    posts.value = it
                    posts.removeSource(source)
                }
            }
        }
        return posts
    }
}