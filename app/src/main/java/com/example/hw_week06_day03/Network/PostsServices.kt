package com.example.hw_week06_day03.Network

import com.example.hw_week06_day03.Model.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PostsServices {

    //- Get all posts
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    //- Add new posts
    @POST("posts")
    fun insertPosts(@Body post: Post): Call<Post>

    //- Update posts
    @PUT("posts/{id}")
    fun updatePost(@Path("id")id: Int,@Body post: Post): Call<Post>

    //- Delete posts
    @DELETE("posts/{id}")
    fun deletePost(@Path("id")id: Int): Call<Post>

    companion object {
        var BASE_URL = "https://61938d0dd3ae6d0017da866b.mockapi.io/api/"

        fun create() : PostsServices {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(PostsServices::class.java)
        }
    }
}