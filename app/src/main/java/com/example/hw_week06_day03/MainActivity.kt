package com.example.hw_week06_day03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week06_day03.Adapter.PostAdapter
import com.example.hw_week06_day03.Model.Post
import com.example.hw_week06_day03.Model.PostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.fabAddPost).setOnClickListener {
            val intent = Intent(this, AddPost::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        val recycledViewPosts = findViewById<RecyclerView>(R.id.recycledViewPosts)
        recycledViewPosts.layoutManager = LinearLayoutManager(this)

        PostViewModel().getAllPosts().observe(this,{
            recycledViewPosts.adapter = PostAdapter(this@MainActivity, it as MutableList<Post>)
        })
    }


}