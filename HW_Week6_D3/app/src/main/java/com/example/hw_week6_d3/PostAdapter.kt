package com.example.hw_week6_d3

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week6_d3.Model.Post
import com.example.hw_week6_d3.Network.PostAPI
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostAdapter(var data: MutableList<Post>) : RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.name.text = data[position].name
        holder.body.text = data[position].postBody
        holder.likes.text = data[position].likes.toString()
        Picasso.get().load(data[position].avatar).into(holder.avatar)

        holder.itemView.setOnClickListener {
        var intent = Intent(holder.itemView.context,PostDetailsActivity::class.java)
        intent.putExtra("posts",data[position])
        holder.itemView.context.startActivity(intent)
        }
        holder.likes.setOnCheckedChangeListener { buttonView, isChecked ->
            // Updating the API's likes count
            var updatedPost = data[position]
            if (isChecked) {
                updatedPost.likes ++
            } else {
                updatedPost.likes --
            }

            val call = PostAPI.post.updatePosts(updatedPost, updatedPost.id)

            call.enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        holder.likes.text = updatedPost.likes.toString()
                        Toast.makeText(holder.itemView.context, "Post has been updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(holder.itemView.context, "Like was not submitted", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    print(t.message)
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class PostHolder(v: View) : RecyclerView.ViewHolder(v) {

    var avatar = v.findViewById<ImageView>(R.id.imageViewAvatar)
    var name = v.findViewById<TextView>(R.id.textViewName)
    var body = v.findViewById<TextView>(R.id.textViewBody)
    var likes = v.findViewById<CheckBox>(R.id.checkBoxLike)

}