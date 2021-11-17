package com.example.hw_week6_day3_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.hw_week6_day3_retrofit.Model.Post
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class PostDeta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_deta)

        var post = findViewById<TextView>(R.id.postBodyDeta)
        var user = findViewById<TextView>(R.id.UserName)
        var avatar =findViewById<ImageView>(R.id.UserAvatar)
        var deleBut =findViewById<Button>(R.id.buttonDelete)
        var editBut =findViewById<Button>(R.id.buttonEdit)


       var t= intent.getStringExtra("post") as Post
        post.text=t.postBody
        user.text=t.name

        Picasso.get().load(t.avatar).into(avatar)


        deleBut.setOnClickListener {  }

        editBut.setOnClickListener {  }


    }
}