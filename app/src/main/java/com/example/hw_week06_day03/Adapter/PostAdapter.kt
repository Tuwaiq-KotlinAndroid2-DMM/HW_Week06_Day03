package com.example.hw_week06_day03.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week06_day03.EditPost
import com.example.hw_week06_day03.MainActivity
import com.example.hw_week06_day03.Model.Post
import com.example.hw_week06_day03.R
import com.squareup.picasso.Picasso

class PostAdapter(var context: Context, var data: MutableList<Post>) : RecyclerView.Adapter<PostAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, null)
        return PostAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: PostAdapterHolder, position: Int) {
        holder.username.text = data[position].name
        Picasso.get().load(Uri.parse(data[position].avatar)).into(holder.avatar)
        holder.likes.text = data[position].likes.toString() + " likes"
        holder.content.text = data[position].postBody

        holder.itemView.setOnClickListener {
            val intent = Intent(context, EditPost::class.java)
            intent.putExtra("post",data[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = data.size
}

class PostAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var username = v.findViewById<TextView>(R.id.textViewUsername)
    var avatar = v.findViewById<ImageView>(R.id.imageViewAvatar)
    var likes = v.findViewById<TextView>(R.id.textViewLikes)
    var content = v.findViewById<TextView>(R.id.textViewContent)
}

