package com.example.homeworkweek6day3

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkweek6day3.Model.Post
import com.example.homeworkweek6day3.network.Posts
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postService: Posts = retrofit.create(Posts::class.java)


        postService.getAllPost().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var list = response.body()
                myRecyclerView.adapter = PostAdapter(list!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }

        })
        

            var post = Post("https://cdn.fakercloud.com/avatars/axel_128.jpg", "202", 23, "Lily", "New Pic")
            postService.AddToPost(post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "added", Toast.LENGTH_SHORT).show()

                        print("Added")
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                }

            })



            var put = Post(
                "https://cdn.fakercloud.com/avatars/collegeman_128.jpg",
                "3",
                50,
                "Kay J",
                "Expedita possimus neque dolor. Sint sequi molestiae doloremque rem soluta delectus libero."
            )
            postService.updatePost(post.id, post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        val retrivedPost = response.body()
                        myRecyclerView.adapter?.notifyDataSetChanged()
                        Toast.makeText(
                            this@MainActivity,
                            "Updated Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("UpdatePost", "Update Post Successfully")
                        Log.d("UpdatePost", retrivedPost.toString())
                    } else {
                        Log.d("Upadte post successful", response.code().toString())
                        Toast.makeText(this@MainActivity, "Not Updated", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                }

            })


     postService.deletePost("42").enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        val retrivedPost = response.body()
                        myRecyclerView.adapter?.notifyDataSetChanged()
                        Toast.makeText(
                            this@MainActivity,
                            "Deleted Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("DeletePost", "Delete Post Successfully")
                        Log.d("DeletePost", retrivedPost.toString())
                    } else {
                        Log.d("Delete post successful", response.code().toString())
                        Toast.makeText(this@MainActivity, "Not Deleted", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()

                }

            })

        }

    }

