package com.example.hwwee6d3_socialappandretrofit.network

import com.example.hwwee6d3_socialappandretrofit.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @POST("posts")
    fun addPost(@Body post: Post): Call<Post>

    @PUT("posts")
    fun updatePost(@Body post: Post): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int): Call<Post>
}