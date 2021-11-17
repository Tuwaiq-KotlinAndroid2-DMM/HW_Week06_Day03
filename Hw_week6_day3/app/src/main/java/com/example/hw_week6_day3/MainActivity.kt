package com.example.hw_week6_day3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var postRecyclerView = findViewById<RecyclerView>(R.id.postRecyclerView)
        var textViewName = findViewById<TextView>(R.id.textViewName)
        var textViewPost = findViewById<TextView>(R.id.textViewPost)
        var textViewLikeNum = findViewById<TextView>(R.id.textViewLikeNum)
        var buttonAdd = findViewById<Button>(R.id.buttonAdd)
        var buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        var buttonDelete = findViewById<Button>(R.id.buttonDelete)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var PostMethod = retrofit.create(PostMethod::class.java)
        postRecyclerView.layoutManager = LinearLayoutManager(this)
//        val layoutManager = LinearLayoutManager(this)
//        postRecyclerView.setLayoutManager(layoutManager)
//        postRecyclerView.setHasFixedSize(true)

        val postsList = mutableListOf<Post>()
        PostMethod.getAllPost().enqueue(object :Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val postList=response.body()
                postRecyclerView.adapter=postAdapter(postList as List<Post>)
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        postRecyclerView.adapter=postAdapter(postsList)

/*        PostMethod.getAllPost().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

              *//*  val postList = response.body()
                postRecyclerView.adapter=postAdapter(postList as List<Post>)*//*
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }


        })*/


        buttonAdd.setOnClickListener {
            var post = Post(
                "https://upload.wikimedia.org/wikipedia/commons/e/e1/Noha_al_yousef_personal_photo.jpg",
                "70",
                50,
                "Ms.Noha Alyousef",
                "\"Non non itaque. Facere et quod. Dicta tempora inventore labore. Aliquam dicta dignissimos dignissimos et occaecati vero tempore eum.\""
            )
            PostMethod.addPost(post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }
            })

        }

        buttonUpdate.setOnClickListener {
            var post = Post(
                "https://upload.wikimedia.org/wikipedia/commons/e/e1/Noha_al_yousef_personal_photo.jpg",
                "56",
                50,
                "Ms.Noha Alyousef",
                "\"Non non itaque.\""
            )
            PostMethod.updatePost(post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }

            })
        }

        buttonDelete.setOnClickListener {

            PostMethod.deletePost(55).enqueue(object :Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                   Toast.makeText(this@MainActivity,"Post Deleted",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }
            }

            )
        }
    }

}