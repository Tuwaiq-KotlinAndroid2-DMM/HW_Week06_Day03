package com.example.retrofithw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val RecyclerViewpost = findViewById<RecyclerView>(R.id.RecyclerViewPost)
        RecyclerViewpost.layoutManager = LinearLayoutManager(this)

        var retrofit=Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var Post=retrofit.create((Posts::class.java))

        //******************************************************************

            Post.getAllProducts()
                .enqueue(object :Callback<List<post>>{ override fun onResponse(call: Call<List<post>>,
                 response: Response<List<post>>
                    ) {
                    var list = response.body()
                    RecyclerViewpost.adapter = AdaptePost(list!!)
                    }
                    override fun onFailure(call: Call<List<post>>, t: Throwable) {}
                })

        //******************************************************************

            var post=post("https://cdn.fakercloud.com/avatars/rickyyean_128.jpg","1100"
                ,600,"hello","how are you ")
            Post.addPost(post).enqueue(object :Callback<post>{
                override fun onResponse(call: Call<post>, response: Response<post>) {
                    if(response.isSuccessful)
                    Toast.makeText(this@MainActivity,"Post has been Successful add",Toast.LENGTH_SHORT)
                    println("Post has been Successful add") }
                override fun onFailure(call: Call<post>, t: Throwable) {} })

        //******************************************************************


          var posts=post("Ms.Fton Salih","1122",700,"ffff","Monday")
          Post.updatePost(posts.id,posts)
              .enqueue(object :Callback<post>{
                  override fun onResponse(call: Call<post>, response: Response<post>) {
                     if (response.isSuccessful)
                         Toast.makeText(this@MainActivity,"Successful Update Post",Toast.LENGTH_SHORT)
                      println("Successful Update Post")
                  }

                  override fun onFailure(call: Call<post>, t: Throwable) {
                  }

              })

        //******************************************************************

            Post.deletePost("2")
                .enqueue(object :Callback<post>{
                    override fun onResponse(call: Call<post>, response: Response<post>) {
                        if(response.isSuccessful)
                        Toast.makeText(this@MainActivity,"Successful Delete Post",Toast.LENGTH_SHORT)
                        println("Successful Delete Post")
                    }

                    override fun onFailure(call: Call<post>, t: Throwable) {

                    }
                })




    }
}