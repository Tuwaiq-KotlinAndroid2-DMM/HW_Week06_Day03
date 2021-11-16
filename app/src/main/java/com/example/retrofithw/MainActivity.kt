package com.example.retrofithw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textViwe=findViewById<TextView>(R.id.textViwe)
        var btnAllpost=findViewById<Button>(R.id.buttonAllpost)
        var btnAdd=findViewById<Button>(R.id.buttonAdd)
        var btnUpdat=findViewById<Button>(R.id.buttonUpdat)
        var btnDelete=findViewById<Button>(R.id.buttonDelete)

        var retrofit=Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Post=retrofit.create((Posts::class.java))


        btnAllpost.setOnClickListener {
            Post.getAllProducts()
                .enqueue(object :Callback<List<post>>{
                    override fun onResponse(
                        call: Call<List<post>>,
                        response: Response<List<post>>
                    ) {
                        var list=response.body()
                        textViwe.text= list?.get(0)?.name
                    }

                    override fun onFailure(call: Call<List<post>>, t: Throwable) {

                    }
                })
        }

        btnAdd.setOnClickListener {
            var posts=post("Ms.Mzon Salih","1100",600,"mmmm","Monday")
            Post.addPost(posts).enqueue(object :Callback<post>{
                override fun onResponse(call: Call<post>, response: Response<post>) {
                    if(response.isSuccessful)
                    Toast.makeText(this@MainActivity,"Post has been Successful add",Toast.LENGTH_SHORT)
                    println("Post has been Successful add")
                }

                override fun onFailure(call: Call<post>, t: Throwable) {

                }

            })

        }

      btnUpdat.setOnClickListener {
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



      }

        btnDelete.setOnClickListener {
            Post.deletePost("2")
                .enqueue(object :Callback<post>{
                    override fun onResponse(call: Call<post>, response: Response<post>) {
                        if(response.isSuccessful)
                        //val deletePost=response.body()
                        Toast.makeText(this@MainActivity,"Successful Delete Post",Toast.LENGTH_SHORT)
                        println("Successful Delete Post")
                    }

                    override fun onFailure(call: Call<post>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })



    }
}}