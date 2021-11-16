package com.example.hw_week06_day03

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.hw_week06_day03.Model.Post
import com.example.hw_week06_day03.Model.PostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class EditPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)

        val intent = intent.getSerializableExtra("post") as Post

        val postImageView = findViewById<ImageView>(R.id.imageViewEditImageUrl)
        Picasso.get().load(Uri.parse(intent.avatar)).into(postImageView)

        val imageEditText = findViewById<EditText>(R.id.editTextEditImageUrl)
        imageEditText.setText(intent.avatar)
        val usernameEditText = findViewById<EditText>(R.id.editTextEditUsername)
        usernameEditText.setText(intent.name)
        val contentEditText =findViewById<EditText>(R.id.editTextEditContent)
        contentEditText.setText(intent.postBody)

        imageEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus != true){
                Picasso.get().load(Uri.parse(imageEditText.text.toString()))
                    .into(postImageView)
            }
        }

        findViewById<Button>(R.id.buttonEditPost).setOnClickListener {
            val avatar = imageEditText.text.toString()
            val name = usernameEditText.text.toString()
            val content = contentEditText.text.toString()

            val post = Post(avatar,"1",0,name,content)
            PostViewModel().updateThePost(intent.id.toInt(), post).observe(this,{
                Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            })
        }

        findViewById<FloatingActionButton>(R.id.fabDeletePost).setOnClickListener {
            PostViewModel().deleteThePost(intent.id.toInt()).observe(this,{
                Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()
                finish()
            })
        }
    }
}