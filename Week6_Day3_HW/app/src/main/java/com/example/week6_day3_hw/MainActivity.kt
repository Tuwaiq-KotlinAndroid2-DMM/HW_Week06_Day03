package com.example.week6_day3_hw

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wee.Adapter
import com.example.week6_day3_hw.Model.Post
import com.example.week6_day3_hw.network.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var retrofit= Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        var postService= retrofit.create(PostService::class.java)

        var mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        var addBtn=findViewById<Button>(R.id.buttonAdd)
        var updateBtn=findViewById<Button>(R.id.buttonUpdate)
        var deleteBtn=findViewById<Button>(R.id.buttonDelete)






        //get all posts

        val livedataPost = MutableLiveData<List<Post>>()
        postService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                var postList=response.body()
                mRecyclerView.adapter=Adapter(postList!! as MutableList<Post>)
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })



            fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }



        // add posts

    addBtn.setOnClickListener {
    var po=Post("https://cdn.fakercloud.com/avatars/collegeman_128.jpg","1",70,"Fatima","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout")
    postService.addPosts(po).enqueue(object : Callback<Post>{
        override fun onResponse(call: Call<Post>, response: Response<Post>) {
            if(response.isSuccessful){
                Toast.makeText(this@MainActivity, "added successfully", Toast.LENGTH_SHORT).show()
                println("added successfully")
            }}

        override fun onFailure(call: Call<Post>, t: Throwable) {
            TODO("Not yet implemented")
        }


    })

}

        //update posts

        updateBtn.setOnClickListener {
            var po=Post("https://cdn.fakercloud.com/avatars/collegeman_128.jpg","",100,"Ali","It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout")
            postService.updatePosts(59,po).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "Update Successfully", Toast.LENGTH_SHORT).show()

                 }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })



        }

        //delete post

        deleteBtn.setOnClickListener {
            postService.deletePosts(59).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(this@MainActivity, "delete successfully", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }











    }
}