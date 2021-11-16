package com.example.wee

import com.example.week6_day3_hw.R


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week6_day3_hw.Model.Post
import com.squareup.picasso.Picasso

class Adapter(var data: MutableList<Post>) : RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_post, parent, false)
         return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.postNameTextView.text= data[position].name
        holder.postBodyTextView.text= data[position].postBody
        holder.postLikeTextView.text= data[position].likes.toString()
        Picasso.get().load(data[position].avatar).into(holder.postAvatarImageView)


    }

    override fun getItemCount(): Int {
        return data.size
     }

}




class PostHolder(v: View):RecyclerView.ViewHolder(v) {
    var postNameTextView= v.findViewById<TextView>(R.id.textViewName)
    var postBodyTextView= v.findViewById<TextView>(R.id.textViewBody)
    var postLikeTextView= v.findViewById<TextView>(R.id.textViewLike)
    var postAvatarImageView= v.findViewById<ImageView>(R.id.imageViewAvatar)




}