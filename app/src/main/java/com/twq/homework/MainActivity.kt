package com.twq.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twq.homework.model.Adapter
import com.twq.homework.model.Post
import com.twq.homework.network.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder()
                .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val productService: Posts = retrofit.create(Posts::class.java)

        // Get
        productService.getAllPosts().enqueue(object :Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var list = response.body()
                mRecyclerView.adapter = Adapter(list!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                d("Ali","Failure")

            }

        })

        // Post
        var post = Post("https://upload.wikimedia.org/wikipedia/commons/9/98/Pablo_picasso_1.jpg",
            "100",23,"Picasso","A picture for the Artist picasso")
        productService.addPost(post).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Added", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
            }

        })
        // Put
        var put = Post("https://upload.wikimedia.org/wikipedia/commons/9/98/Pablo_picasso_1.jpg",
            "101",123,"Picasso2","Updating A picture for the Artist picasso")
        productService.updatePost(post.id,put).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val updatedPost = response.body()
                    mRecyclerView.adapter?.notifyDataSetChanged()
                    Log.d("Doc", "$updatedPost")
                    Toast.makeText(this@MainActivity, "Updated", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
            }
        })

        productService.deletePost("101").enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val deletedPost = response.body()
                    mRecyclerView.adapter?.notifyDataSetChanged()
                    Log.d("Doc", "Deleted $deletedPost")
                    Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
            }
        })



        }
}
