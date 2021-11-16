package com.example.hw_week06_day03.Model

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw_week06_day03.Network.PostsServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel: ViewModel() {

    fun getAllPosts(): MutableLiveData<List<Post>> {
        val livedataPost = MutableLiveData<List<Post>>()
        PostsServices.create().getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?){
                Log.d(TAG,"Statues: ${response?.isSuccessful}")
                if (response?.body() != null) {
                    val products = response?.body()
                    Log.d(TAG,"Statues: ${products}")
                    livedataPost.postValue(products)
                }
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
            }
        })
        return livedataPost
    }

    fun insertNewPost(post: Post){}

    fun updateThePost(post: Post){}

    fun deleteThePost(id: Int){}

}