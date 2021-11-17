package com.example.hw_week6_day3_retrofit.Model

import java.io.Serializable

data class Post(
    val avatar: String,
    val id: String,
    val likes: Int,
    val name: String,
    val postBody: String
):Serializable