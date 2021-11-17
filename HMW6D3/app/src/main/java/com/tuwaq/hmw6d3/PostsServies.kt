package com.tuwaq.hmw6d3

import retrofit2.Call
import retrofit2.http.*

interface PostsServies {
    @GET("Posts")
    fun getAllPost(): Call<List<Post>>

    @POST("Posts")
    fun addPost(@Body post:Post):Call<Post>

    @PUT("Posts/name")
    fun updatePost(@Part("name") name: String, posts: Post):Call<Post>


    @DELETE("Posts")
    fun deletePost(@Body post: Int): Call<Post>

}