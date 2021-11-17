package com.twq.hwapi.Network

import com.twq.hwapi.Model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @POST("posts")
    fun addPost(@Body product:Post): Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") id: String, @Body post: Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: String): Call<Post>
}