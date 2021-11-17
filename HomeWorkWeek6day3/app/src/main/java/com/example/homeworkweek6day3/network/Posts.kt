package com.example.homeworkweek6day3.network

import com.example.homeworkweek6day3.Model.Post
import retrofit2.Call
import retrofit2.http.*

interface Posts {

    @GET("posts")
    fun getAllPost(): Call<List<Post>>

    @POST("posts")
    fun AddToPost(@Body post: Post): Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: String?, @Body post: Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id")id: String): Call<Post>

}