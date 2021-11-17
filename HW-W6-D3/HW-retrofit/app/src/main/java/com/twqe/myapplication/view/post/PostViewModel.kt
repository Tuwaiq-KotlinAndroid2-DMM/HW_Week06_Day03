package com.twqe.myapplication.view.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twqe.myapplication.modle.Post
import com.twqe.myapplication.repository.PostRepository

class PostViewModel : ViewModel() {


    var postRepository=PostRepository()

    fun getPosts(): LiveData<List<Post>> {
        return postRepository.getAllPosts()
    }
}