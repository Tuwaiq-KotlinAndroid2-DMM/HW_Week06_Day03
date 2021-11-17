package com.example.hw_week6_day3_retrofit.Network

import com.example.hw_week6_day3_retrofit.Model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @POST("posts")
    fun addNewPost(@Body post :Post): Call<Post>

     @PUT("posts/{id}")
    fun updatePost(@Path("id") id:Int, @Body post :Post): Call<Post>


    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int): Call<Post>



}
