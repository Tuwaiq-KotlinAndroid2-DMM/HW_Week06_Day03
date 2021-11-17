package com.tuwaq.hmw6d3

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

        var textView = findViewById<TextView>(R.id.textView)
        var btnAllpost = findViewById<Button>(R.id.button)
        var buttonAdd = findViewById<Button>(R.id.buttonAdd)
        var buttonDelete = findViewById<Button>(R.id.buttonDelet)
        var buttonUpdate = findViewById<Button>(R.id.buttonUpdate)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/posts")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var PostsServies = retrofit.create(PostsServies::class.java)
        btnAllpost.setOnClickListener {

            PostsServies.getAllPost().enqueue(object : Callback<List<Post>> {
                override fun onResponse(
                    call: Call<List<Post>>,
                    response: Response<List<Post>>
                ) {
                    var list = response.body()
                    textView.text = list?.get(0)?.name
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                }
            })
        }

        buttonAdd.setOnClickListener {

            var posts = Post(
                "https://eitrawmaterials.eu/wp-content/uploads/2016/09/person-icon.png",
                "99", 10, "SHARIFAH", "TEST"
            )
            PostsServies.addPost(posts).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {

                    if (response.isSuccessful)
                        Toast.makeText(this@MainActivity, "successful", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {

                }

            })
        }

        buttonUpdate.setOnClickListener {
            var posts = Post("Sharifah", "111", 70, "sharifah", "test")
            PostsServies.updatePost(posts.id, posts)
                .enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful)
                            Toast.makeText(
                                this@MainActivity,
                                "Successful Update Post",
                                Toast.LENGTH_SHORT
                            )
                        println("Successful Update Post")
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                    }

                })


        }



        buttonDelete.setOnClickListener {
            PostsServies.deletePost(98)
                .enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful)

                            Toast.makeText(
                                this@MainActivity,
                                "Successful deleted post",
                                Toast.LENGTH_SHORT
                            )
                        println("Successful deleted post")
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {

                    }
                })


        }
    }
}
