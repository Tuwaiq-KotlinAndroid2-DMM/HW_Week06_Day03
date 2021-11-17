package com.example.hww6d3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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
    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // var textView=findViewById<TextView>(R.id.textView2)
        var addpost=findViewById<Button>(R.id.addpost)
        var update=findViewById<ImageView>(R.id.update)
        var deletdpost=findViewById<Button>(R.id.deletdpost)
        mRecyclerView = findViewById(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        var retrofit= Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


       var postService= retrofit.create(PostService::class.java)
        postService.getAllPost().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var postList=response.body()
             mRecyclerView.adapter=postAdapter(postList!!)
                //textView.text=postList?.get(0)?.name
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }

        })




        addpost.setOnClickListener {
            var postt=Post("https://upload.3dlat.net/uploads/13686431781.jpg","0",200,"Pizza","Pizza is an Italian food that was created in Italy ")
            postService.addPost(postt).enqueue(object : Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@MainActivity, " successfully", Toast.LENGTH_SHORT).show()
                        println("added successfully")
                    }}

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })

        }
        deletdpost.setOnClickListener {
            postService.deletePosts("128").enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, " successfully", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
        update.setOnClickListener {
            var postt=Post("http://cdn.shopify.com/s/files/1/0407/3422/8631/products/c3778c9d-bfc9-492e-8d49-a360bb7607f4_1200x1200.jpg?v=1597446216","90",1000,"Burger","Burger Is The Most Famous Fast Food")
            postService.updatePosts("90",postt).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, " Successfully", Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                }

            })



        }
    }
}