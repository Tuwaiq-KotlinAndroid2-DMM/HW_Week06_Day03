package com.example.hwwee6d3_socialappandretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hwwee6d3_socialappandretrofit.model.Post
import com.example.hwwee6d3_socialappandretrofit.model.PostAdapter
import com.example.hwwee6d3_socialappandretrofit.network.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postService = retrofit.create(PostService::class.java)
        val pRecycleView = findViewById<RecyclerView>(R.id.pRecycleView)
        pRecycleView.layoutManager = LinearLayoutManager(this)
        val postsList = mutableListOf<Post>()

        // Get all posts

        postService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val postList = response.body()
                pRecycleView.adapter = PostAdapter(postList as List<Post>)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }
        })


        //  (Post) Add new posts

        var buttonPost = findViewById<Button>(R.id.buttonPost)
        buttonPost.setOnClickListener {
            val post = Post(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Paris_RER_E_icon.svg/1200px-Paris_RER_E_icon.svg.png",
                "7",
                99,
                "Esra",
                "Good Morning everyone"
            )

            postService.addPost(post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(
                        this@MainActivity,
                        "The post was added succsufly!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {}

            })
        }
        pRecycleView.adapter = PostAdapter(postsList)
        // (Put) Update posts

        val buttonPut = findViewById<Button>(R.id.buttonPut)
        buttonPut.setOnClickListener {
            val put = Post(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Paris_RER_E_icon.svg/1200px-Paris_RER_E_icon.svg.pnghttps://cdn.fakercloud.com/avatars/nitinhayaran_128.jpg",
                "28",
                90,
                "Wayne",
                "Et aperiam minima rerum nemo porro laboriosam ipsam laboriosam reiciendis. Et facere accusantium. Neque doloremque a et eos velit laudantium nobis."
            )
            postService.updatePost(put).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "The post updated succsufly!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {}
            })
        }
        /*

        "name": "Wayne Dach MD",
        "avatar": "https://cdn.fakercloud.com/avatars/nitinhayaran_128.jpg",
        "postBody": "Et aperiam minima rerum nemo porro laboriosam ipsam laboriosam reiciendis. Et facere accusantium. Neque doloremque a et eos velit laudantium nobis.",
        "likes": 99,
        "id": "28"

         */
        // Delete posts

        var buttonDelete = findViewById<Button>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            postService.deletePost(31).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "The post was deleted succsufly!", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                }

            })

        }
    }
}
