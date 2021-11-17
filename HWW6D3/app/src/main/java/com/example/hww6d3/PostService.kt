package com.example.hww6d3

import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("Posts")
    fun getAllPost(): Call<List<Post>>

    @POST ("Posts")
    fun addPost(@Body post:Post):Call<Post>

    @PUT("Posts/{id}")
    fun updatePosts(@Path("id")id:String, @Body post: Post):Call<Post>


    @DELETE("Posts/{id}")
    fun deletePosts(@Path("id")id:String):Call<Post>
}