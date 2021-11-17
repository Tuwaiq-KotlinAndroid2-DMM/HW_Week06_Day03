package com.twqe.myapplication.view.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twqe.myapplication.R
import androidx.activity.viewModels
import com.twqe.myapplication.postAdapter

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val viewModel: PostViewModel by viewModels()
        var pRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        pRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getPosts().observe(this, { list ->
            pRecyclerView.adapter = postAdapter(list)
        })
        var btnEdit = findViewById<Button>(R.id.btnlPut)
        btnEdit.setOnClickListener {
            viewModel.EditPost()
        }
        var btnAdd = findViewById<Button>(R.id.btnpost)
        btnAdd.setOnClickListener {
            viewModel.AddPost()
        }

        var buttonDelete = findViewById<Button>(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            viewModel.DeletePost()
        }
    }
}