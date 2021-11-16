package com.example.retrofithw

import retrofit2.Call
import retrofit2.http.*

interface Posts {

    @GET("posts")
    fun getAllProducts() : Call<List<post>>

    @POST("posts")
    fun addPost(@Body posts: post):Call<post>

    @PUT("posts/{id}")
    fun updatePost(@Path ("id") id:String? ,@Body posts: post):Call<post>


    @DELETE("posts/{id}")
    fun deletePost(@Path ("id") id:String? ):Call<post>
}