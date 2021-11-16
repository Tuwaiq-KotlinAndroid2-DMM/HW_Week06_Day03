package com.example.hw_week6_day3_solution

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
        var button = findViewById<Button>(R.id.button)
        var buttonAdd = findViewById<Button>(R.id.buttonAdd)
        var buttonDelete = findViewById<Button>(R.id.buttonDelete)
        var buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/api/posts")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var PostsServies = retrofit.create(PostsServies::class.java)
        button.setOnClickListener {

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
                "273", 60, "Dalia", "PC"
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
            var posts=Post("Dalia Alzahrani","3344",230,"name01","something")
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

        buttonDelete.setOnClickListener {
            Post.deletePost("273")
                .enqueue(object :Callback<Post>{
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if(response.isSuccessful)

                            Toast.makeText(this@MainActivity,"Successful deleted post",Toast.LENGTH_SHORT)
                        println("Successful deleted post")
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {

                    }
                })



        }
    }
}