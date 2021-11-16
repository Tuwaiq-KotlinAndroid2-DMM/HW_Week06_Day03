package com.example.w6_d3_homework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w6_d3_homework.model.Post
import com.example.w6_d3_homework.network.PostService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        fabAdd.setOnClickListener {
            var i = Intent(this, AddPost::class.java)
            startActivity(i)
        }

        var retorfit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var postService = retorfit.create(PostService::class.java)

        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        //var postList: MutableList<Post>

        postService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var postList = response.body()
                mRecyclerView.adapter = PostAdapter(postList!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        /*postService.updateProduct(
            68, Post(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-kEMUrjjp-dl3Y1q5b-lNC_m10w_ta96cJA&usqp=CAU",
                "68", 100, "Ghufran Ali", "Update test"
            )
        ).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Toast.makeText(this@MainActivity, "Update Successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

        }) */


    }
}