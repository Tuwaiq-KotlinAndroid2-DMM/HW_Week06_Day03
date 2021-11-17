package com.example.hww6d3

import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("posts")
    fun getAllPost(): Call<List<Post>>

    @POST ("posts")
    fun addPost(@Body post:Post):Call<Post>

    @PUT("posts/{id}")
    fun updatePosts(@Path("id")id:String, @Body post: Post):Call<Post>


    @DELETE("posts/{id}")
    fun deletePosts(@Path("id")id:String):Call<Post>
}