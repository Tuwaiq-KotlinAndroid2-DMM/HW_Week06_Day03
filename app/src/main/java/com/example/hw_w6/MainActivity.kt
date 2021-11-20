package com.example.hw_w6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerViewpost= findViewById<RecyclerView>(R.id.recyclerView)
        var textView=findViewById<TextView>(R.id.textView)
        var btnadd=findViewById<Button>(R.id.btnadd)
        var btnallpost=findViewById<Button>(R.id.btnallpost)
        var btndelete= findViewById<Button>(R.id.btndelete)
        var btnupdate= findViewById<Button>(R.id.btnupdate)

        recyclerViewpost.layoutManager= LinearLayoutManager(this)
        var retrofit= Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Post=retrofit.create((Posts::class.java))

        btnallpost.setOnClickListener {
            Post.getallpruduct()
                .enqueue(object : Callback<List<post>> {
                    override fun onResponse(
                        call: Call<List<post>>,
                        response: Response<List<post>>
                        .enqueue(object :Callback<List<post>>{ override fun onResponse(call: Call<List<post>>,
                                                                                       response: Response<List<post>>
                    ) {
                        var list=response.body()
                        textViwe.text= list?.get(0)?.name
                    }

                        override fun onFailure(call: Call<List<post>>, t: Throwable) {

                            var list = response.body()
                            recyclerViewpost.adapter = AdapterPost(list!!)
                        }
                        override fun onFailure(call: Call<List<post>>, t: Throwable) {}
                    })
                }

                        btnadd.setOnClickListener {
                    var posts=post("bebasager","447",600,"mmmm","Monday")
                    Post.addPost(posts).enqueue(object :Callback<post>{
                        //******************************************************************

                        var post=post("https://cdn.fakercloud.com/avatars/rickyyean_128.jpg","1100"
                            ,600,"hello","how are you ")
                        Post.addPost(post).enqueue(object :Callback<post>{
                            override fun onResponse(call: Call<post>, response: Response<post>) {
                                if(response.isSuccessful)
                                    Toast.makeText(this@MainActivity,"Post has been Successful add",Toast.LENGTH_SHORT)
                                println("Post has been Successful add")
                            }
                            println("Post has been Successful add") }
                        override fun onFailure(call: Call<post>, t: Throwable) {} })

                    override fun onFailure(call: Call<post>, t: Throwable) {
                        //******************************************************************

                    }

                })

        }

        btnupdate.setOnClickListener {
            var posts=post("bduor alsager","4785",700,"bbb","sunday")
            Post.updatePost(posts.id,posts)
                .enqueue(object :Callback<post>{


        }

                        btndelete.setOnClickListener {
                    Post.deletePost("8")
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
}
