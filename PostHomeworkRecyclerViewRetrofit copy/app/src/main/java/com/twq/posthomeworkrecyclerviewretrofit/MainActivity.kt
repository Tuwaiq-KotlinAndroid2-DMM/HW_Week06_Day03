package com.twq.posthomeworkrecyclerviewretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.posthomeworkrecyclerviewretrofit.adapter.PostAdapter
import com.twq.posthomeworkrecyclerviewretrofit.model.Post
import com.twq.posthomeworkrecyclerviewretrofit.network.PostsServices
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

lateinit var postsServices: PostsServices

class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnAdd = findViewById<Button>(R.id.buttonAdd)
        mRecyclerView = findViewById(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)


        var retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        postsServices = retrofit.create(PostsServices::class.java)
        var x = postsServices.getAllPosts().enqueue(object : Callback<MutableList<Post>> {
            override fun onResponse(
                call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>
            ) {
                mRecyclerView.adapter = PostAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {

            }
        })

        btnAdd.setOnClickListener {
            var post = Post(
                "https://avatarfiles.alphacoders.com/164/164000.png", "1", 500, "GOAT",
                "this post was added by: Mohammed")

                postsServices.addPost(post).enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        var retreavedPost = response.body()
                        Log.d("added?","yes")
                        Toast.makeText(this@MainActivity, "added Successfully", Toast.LENGTH_SHORT).show()
                        Log.d("added", retreavedPost.toString())
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.d("added?","No")
                    }

                })

        }


    }
}