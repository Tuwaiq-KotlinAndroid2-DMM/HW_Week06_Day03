package com.example.hw_week06_day03.Model

import java.io.Serializable

data class Post(
    val avatar: String,
    val id: String,
    val likes: Int,
    val name: String,
    val postBody: String
): Serializable