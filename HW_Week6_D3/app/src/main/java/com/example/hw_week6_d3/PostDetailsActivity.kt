package com.example.hw_week6_d3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.hw_week6_d3.Model.Post
import com.example.hw_week6_d3.Network.PostAPI
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        var post = intent.getSerializableExtra("posts") as Post
        var postAvatar = findViewById<ImageView>(R.id.imageViewPostAvatar)
        var postName = findViewById<TextView>(R.id.textViewPostName)
        var postBody = findViewById<TextView>(R.id.textViewPostBody)
        var deleteButton = findViewById<Button>(R.id.buttonDelete)
        var updateButton = findViewById<Button>(R.id.buttonUpdate)

        postName.text = post.name
        postBody.text = post.postBody
        Picasso.get().load(post.avatar).into(postAvatar)

        deleteButton.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete Post")
            alertDialog.setMessage("Do you want to delete this post?")
            alertDialog.setPositiveButton("Yes") { dialog, which ->
                var call = PostAPI.post.deletePost(post.id.toString())
                call.enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@PostDetailsActivity, "Post deleted successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@PostDetailsActivity, "Failed to delete post ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Toast.makeText(this@PostDetailsActivity, "Post was not deleted", Toast.LENGTH_SHORT).show()
                    }

                })
            }

            alertDialog.setNegativeButton("No") { dialog, which ->
                dialog.cancel()
            }

            var exitDialog = alertDialog.create()

            exitDialog.show()
        }

        updateButton.setOnClickListener {
            var call = PostAPI.post.updatePosts(post,post.id.toString())
            call.enqueue(object :Callback<Post>{
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if(response.isSuccessful){
                        Toast.makeText(this@PostDetailsActivity, "Post has been updated", Toast.LENGTH_SHORT).show()
                    }
                    else Toast.makeText(this@PostDetailsActivity, "Error occurred while updating", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@PostDetailsActivity, "Post was not updated", Toast.LENGTH_SHORT).show()
                }

            })


        }
    }


    }
