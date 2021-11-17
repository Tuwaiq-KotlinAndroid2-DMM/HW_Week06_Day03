
package com.example.hw_w6day3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var butAdd = findViewById<Button>(R.id.buttonAdd)
        var butDelete = findViewById<Button>(R.id.buttonAdd)
        var butupdate = findViewById<Button>(R.id.buttonUpdate)
        var butGet = findViewById<Button>(R.id.buttonGet)
       var textView=findViewById<TextView>(R.id.textView)

        var retrofit=Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Post=retrofit.create((Posts::class.java))


        butGet.setOnClickListener {
            Post.getAllProducts()
                .enqueue(object : Callback<List<Post>> {
                    override fun onResponse(
                        call: Call<List<Post>>,
                        response: Response<List<Post>>
                    ) {
                        var list=response.body()
                        textView.text= list?.get(0)?.name
                    }

                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                    }
                })
        }

        butAdd.setOnClickListener {
            var posts=Post("Ms.Mzon Salih","1100",600,"mmmm","Monday")
            Post.addPost(posts).enqueue(object :Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if(response.isSuccessful)
                        Toast.makeText(this@MainActivity,"Post has been Successful add",Toast.LENGTH_SHORT)
                    println("Post has been Successful add")
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }

            })

        }

        butupdate.setOnClickListener {
            var posts=Post("Ms.Fton Salih","1122",700,"ffff","Monday")
            Post.updatePost(posts.id,posts)
                .enqueue(object :Callback<Post>{
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful)
                            Toast.makeText(this@MainActivity,"Successful Update Post",Toast.LENGTH_SHORT)
                        println("Successful Update Post")
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                    }

                })



        }

        butDelete.setOnClickListener {
            Post.deletePost("2")
                .enqueue(object :Callback<Post>{
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if(response.isSuccessful)
                        //val deletePost=response.body()
                            Toast.makeText(this@MainActivity,"Successful Delete Post",Toast.LENGTH_SHORT)
                        println("Successful Delete Post")
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })



        }
    }}