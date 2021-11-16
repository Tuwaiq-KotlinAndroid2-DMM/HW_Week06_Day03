package com.example.week6_day3_hw.network

import com.example.week6_day3_hw.Model.Post
import retrofit2.Call
import retrofit2.http.*


interface PostService {
    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @POST("posts")
    fun addPosts(@Body post: Post):Call<Post>

    @PUT("posts/{id}")
    fun updatePosts(@Path("id")id:Int, @Body post: Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePosts(@Path("id")id:Int):Call<Post>





}