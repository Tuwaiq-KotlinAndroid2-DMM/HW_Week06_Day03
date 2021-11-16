package com.example.hw_week06_day03

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.hw_week06_day03.Adapter.PostAdapter
import com.example.hw_week06_day03.Model.Post
import com.example.hw_week06_day03.Model.PostViewModel
import com.squareup.picasso.Picasso

class AddPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val postImageView = findViewById<ImageView>(R.id.imageViewImageUrl)
        val imageEditText = findViewById<EditText>(R.id.editTextImageUrl)
        val usernameEditText = findViewById<EditText>(R.id.editTextUsername)
        val contentEditText =findViewById<EditText>(R.id.editTextContent)

        imageEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus != true){
                Picasso.get().load(Uri.parse(imageEditText.text.toString()))
                    .into(postImageView)
            }
        }

        findViewById<Button>(R.id.buttonPost).setOnClickListener {
            val avatar = imageEditText.text.toString()
            val name = usernameEditText.text.toString()
            val content = contentEditText.text.toString()

            val post = Post(avatar,"1",0,name,content)
            PostViewModel().insertNewPost(post).observe(this,{
                Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
                finish()
            })
        }

    }
}