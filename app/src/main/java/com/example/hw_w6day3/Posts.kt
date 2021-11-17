package com.example.hw_w6day3

import android.telecom.Call
import retrofit2.http.*

interface Posts {

    @GET("posts")
    fun getAllProducts() : Call<List<Post>>

    @POST("posts")
    fun addPost(@Body posts: Post):Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path ("id") id:String? ,@Body posts: Post):Call<Post>


    @DELETE("posts/{id}")
    fun deletePost(@Path ("id") id:String? ):Call<Post>
}
