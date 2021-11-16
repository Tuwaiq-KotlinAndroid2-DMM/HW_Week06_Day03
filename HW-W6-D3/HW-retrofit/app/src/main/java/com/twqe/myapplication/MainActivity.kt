package com.twqe.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twqe.myapplication.modle.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var postService = retrofit.create(PostService::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var postsList = mutableListOf<Post>()
//      all posts
        postService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var postList = response.body()
                recyclerView.adapter = postAdapter(postList as List<Post>)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }
        })

//        add Post
        var btnpost = findViewById<Button>(R.id.btnpost)
        btnpost.setOnClickListener {
            var post = Post(
                "https://cdn.fakercloud.com/avatars/layerssss_128.jpg",
                "90",
                20,
                "Hazel",
                "Voluptates nostrum libero. Repellat at iste dignissimos vitae minus tenetur et repudiandae enim. Et qui nisi assumenda nobis ut corporis fugit."
            )
            postService.addPost(post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "post is added", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {}

            })
        }
        recyclerView.adapter = postAdapter(postsList)

        //        update Post
        var btnlPut = findViewById<Button>(R.id.btnlPut)
        btnlPut.setOnClickListener {
            var post = Post(
                "https://cdn.fakercloud.com/avatars/layerssss_128.jpg",
                "90",
                25,
                "Hazel",
                "Voluptates nostrum libero. Repellat at iste dignissimos vitae minus tenetur et repudiandae enim. Et qui nisi assumenda nobis ut corporis fugit."
            )
            postService.updatePost(post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "post is updated", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {}
            })

        }


// btn delete
        var buttonDelete = findViewById<Button>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            postService.deletePost(20).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "post is deleted", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                }

            })

        }
    }
}