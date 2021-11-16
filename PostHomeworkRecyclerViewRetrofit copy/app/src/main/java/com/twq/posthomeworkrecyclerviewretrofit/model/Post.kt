package com.twq.posthomeworkrecyclerviewretrofit.model

data class Post(
    val avatar: String,
    val id: String,
    val likes: Int,
    val name: String,
    val postBody: String
)