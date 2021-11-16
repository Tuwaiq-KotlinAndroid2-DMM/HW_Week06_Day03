package com.twq.posthomeworkrecyclerviewretrofit.network

import android.app.ActivityManager
import com.twq.posthomeworkrecyclerviewretrofit.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface PostsServices {

    @GET("posts")
    fun getAllPosts (): Call<MutableList<Post>>

    @DELETE("posts/{id}")
    fun deletePost (@Path("id") id: String): Call<Post>

    @PUT("posts/{id}")
    fun updatePost (@Path("id")postBody: String, @Body post: Post): Call<Post>

    @POST("posts")
    fun addPost (@Body post: Post): Call<Post>


}