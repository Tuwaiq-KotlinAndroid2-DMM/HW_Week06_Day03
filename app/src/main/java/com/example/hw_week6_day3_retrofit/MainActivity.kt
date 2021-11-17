package com.example.hw_week6_day3_retrofit

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week6_day3_retrofit.Model.Post
import com.example.hw_week6_day3_retrofit.Network.PostService
import com.example.hw_week6_day3_retrofit.RecyclerView.PostAdapter
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

        var recyclerView=findViewById<RecyclerView>(R.id.mrecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)

        var addButton =findViewById<FloatingActionButton>(R.id.addButton)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/posts/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var postsService =retrofit.create(PostService::class.java)

        postsService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

               var list =response.body()
                recyclerView.adapter=PostAdapter(list!!)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }


        })


        addButton.setOnClickListener {
//          var post :Post("","",,"","")
//            postsService.addNewPost(post).enqueue(object : Callback<Post> {
//                override fun onResponse(call: Call<Post>, response: Response<Post>) {
//
//                    Toast.makeText(this@MainActivity, "Successful", Toast.LENGTH_SHORT).show()
//
//                }
//
//                override fun onFailure(call: Call<Post>, t: Throwable) {
//
//                }
//
//            })
        }

        var dialog = layoutInflater.inflate(
            R.layout.custom_add_d,
            null
        )
        var d = AlertDialog.Builder(this).setView(dialog)
           // .setPositiveButton("Add")
            .create()

        d.show()

    }
}