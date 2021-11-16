package com.example.hw_week06_day03

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week06_day03.Adapter.PostAdapter
import com.example.hw_week06_day03.Model.Post
import com.example.hw_week06_day03.Model.PostViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycledViewPosts = findViewById<RecyclerView>(R.id.recycledViewPosts)
        recycledViewPosts.layoutManager = LinearLayoutManager(this)

        PostViewModel().getAllPosts().observe(this,{
            Log.d(ContentValues.TAG,"setting view: ${it.size}")
            recycledViewPosts.adapter = PostAdapter(it as MutableList<Post>)
        })
    }


}