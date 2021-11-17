package com.twqe.myapplication.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.twqe.myapplication.modle.Post
import com.twqe.myapplication.network.API
import com.twqe.myapplication.network.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository {
    val postService = API.getInstance().create(PostService::class.java)
    var mutableLiveData = MutableLiveData<List<Post>>()

    //    get posts
    fun getAllPosts(): MutableLiveData<List<Post>> {
        val callPostList = postService.getAllPosts()
        callPostList.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }
        })
        return mutableLiveData
    }

//     add post
fun addPost(): MutableLiveData<List<Post>> {
    var post = Post(
        "https://cdn.fakercloud.com/avatars/layerssss_128.jpg",
        "90",
        20,
        "Hazel",
        "Voluptates nostrum libero. Repellat at iste dignissimos vitae minus tenetur et repudiandae enim. Et qui nisi assumenda nobis ut corporis fugit."
    )
    val callAddPost = postService.addPost(post)
    callAddPost.enqueue(object : Callback<Post> {
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            if (response.isSuccessful) {
                mutableLiveData.postValue(response.body() as List<Post>)
            }
        }

        override fun onFailure(call: Call<Post>, t: Throwable) {}

    })
    return mutableLiveData
}

    //             update Post
    fun updatePost(): MutableLiveData<List<Post>> {
        var post = Post(
            "https://cdn.fakercloud.com/avatars/layerssss_128.jpg",
            "90",
            20,
            "Hazel",
            "Voluptates nostrum libero. Repellat at iste dignissimos vitae minus tenetur et repudiandae enim. Et qui nisi assumenda nobis ut corporis fugit."
        )
        val callAddPost = postService.updatePost(post)
        callAddPost.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body() as List<Post>)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {}

        })
        return mutableLiveData
    }

    //    delete Post
    fun deletePost(): MutableLiveData<List<Post>> {
        val callDeletePost = postService.deletePost(20).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                mutableLiveData.postValue(response.body() as List<Post>)
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
            }

        })
        return mutableLiveData
    }
}