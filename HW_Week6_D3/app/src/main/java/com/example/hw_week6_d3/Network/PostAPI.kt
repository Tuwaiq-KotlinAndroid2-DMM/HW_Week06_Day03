package com.example.hw_week6_d3.Network

import com.example.hw_week6_d3.Model.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PostAPI {

    companion object {
        var url = "https://61938d0dd3ae6d0017da866b.mockapi.io/api/"

        var retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var post: PostAPI = retrofit.create(PostAPI::class.java)
    }

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @POST("posts")
    fun newPost (@Body post: Post): Call<Post>

    @PUT("posts/{id}")
    fun updatePosts(@Body post: Post,@Path("id") id:String) : Call<Post>

    @DELETE("posts/{id}")
    fun deletePost (@Path("id") id: String): Call<Post>

}