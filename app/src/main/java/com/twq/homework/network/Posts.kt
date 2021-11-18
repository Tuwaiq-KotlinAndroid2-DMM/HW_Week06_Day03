package com.twq.homework.network

import com.twq.homework.model.Post
import retrofit2.Call
import retrofit2.http.*

interface Posts {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @POST("posts")
    fun addPost(@Body post: Post): Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id")id:String?,@Body post: Post):Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id")id: String?): Call<Post>


//    @GET("Products")
//    fun getAllProducts(): Call<List<Product>>
//
//    @GET("Products/{id}")
//    fun getProductByID(@Path("id")id:Int): Call<Product>
//
//    @GET("Products?")
//    fun searchProduct(@Query("price")price:Int): Call<List<Product>>
//
//    @POST("Products")
//    fun addProduct(@Body product: Product): Call<Product>
//
//    @PUT("Products/{id}")
//    fun updateProduct(@Body product: Product): Call<Product>
}