package com.example.hw_week6_d3.Model

import java.io.Serializable

data class Post(
    val avatar: String?,
    val id: String,
    var likes: Int,
    val name: String,
    val postBody: String
):Serializable