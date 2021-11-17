package com.twq.hwapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.hwapi.Adapter.PostAdapter
import com.twq.hwapi.Model.Post
import com.twq.hwapi.Network.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerViewHome)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var prodectsService = retrofit.create(PostService::class.java)



        prodectsService.getAllPosts().enqueue(object :Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var list = response.body()
                mRecyclerView.adapter = PostAdapter(list!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {


            }

        })


        var post = Post("https://i.ytimg.com/vi/jHWKtQHXVJg/maxresdefault.jpg",
            "135",55,"Cat","Cute cat")
        prodectsService.addPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "has been added", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
            }

        })

        var put = Post("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/VAN_CAT.png/330px-VAN_CAT.png",
            "135",255,"Van cat","Van cats are relatively large, have a chalky white coat")
        prodectsService.updatePost(post.id,put).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val updatedPost = response.body()
                    mRecyclerView.adapter?.notifyDataSetChanged()

                    Toast.makeText(this@MainActivity, "has been updated", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
            }
        })

        prodectsService.deletePost("13").enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val deletedPost = response.body()
                    mRecyclerView.adapter?.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "has been deleted", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
            }
        })

    }
}