package com.example.w6_d3_homework.network

import com.example.w6_d3_homework.model.Post
import retrofit2.Call
import retrofit2.http.*

interface PostService {
    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @DELETE("posts/{id}")
    fun deleteProduct(@Path("id") id:Int):Call<Post>

    @POST("posts")
    fun addPost(@Body post: Post): Call<Post>

    @PUT("posts/{id}")
    fun updateProduct(@Path("id")id:Int,@Body product: Post):Call<Post>

}