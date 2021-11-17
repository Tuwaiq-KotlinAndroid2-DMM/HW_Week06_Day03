package com.example.hw_week6_day3

import retrofit2.Call
import retrofit2.http.*

interface PostMethod {

    @GET("posts")
    fun getAllPost(): Call<List<Post>>

    @POST("posts")
    fun addPost(@Body post: Post): Call<Post>

    @PUT("posts")
    fun updatePost(@Body post: Post): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int):Call<Post>

}