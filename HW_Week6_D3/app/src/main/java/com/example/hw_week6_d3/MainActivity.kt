package com.example.hw_week6_d3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week6_d3.Model.Post
import com.example.hw_week6_d3.Network.PostAPI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addButton = findViewById<FloatingActionButton>(R.id.floatingActionButtonAdd)
        var postRecyclerView = findViewById<RecyclerView>(R.id.postsRecyclerView)
        postRecyclerView.layoutManager = LinearLayoutManager(this)
        var responseList = mutableListOf<Post>()
        var post = PostAPI.post


        post.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                responseList = response.body() as MutableList<Post>
                postRecyclerView.adapter = PostAdapter(responseList)
                Toast.makeText(this@MainActivity, "Response size: ${responseList.size}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to get data from API", Toast.LENGTH_SHORT).show()
                t.message?.let { Log.d("Retrofit", it) }
            }

        })

        addButton.setOnClickListener {
            var newPost = Post("https://i2.wp.com/dl.img-news.com/dl/img/s0/dl/2020/08/haifa-wehbe-800x549.jpg?fit=%2C&ssl=1","555",55,"Haifa","Hey")
            post.newPost(newPost).enqueue(object : Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "Post has been added", Toast.LENGTH_SHORT).show()
                    println("Product has been added")
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                }

            })



        }

    }
}