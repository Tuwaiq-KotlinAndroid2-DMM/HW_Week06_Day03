package com.example.homeworkweek6day3

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkweek6day3.Model.Post
import com.squareup.picasso.Picasso

class PostAdapter(var postList: List<Post>) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false)
        return PostViewHolder(layoutInflater)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.textViewName.text = postList[position].name
        holder.textViewLike.text = postList[position].likes.toString()
        holder.textViewPostBody.text = postList[position].postBody.toString()
        Picasso.get().load(postList[position].avatar).into(holder.imageViewAvatar)

    }

    override fun getItemCount(): Int {
        return postList.size
    }

}
class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var imageViewAvatar = itemView.findViewById<ImageView>(R.id.imageView)
    var textViewName = itemView.findViewById<TextView>(R.id.Name)
    var textViewLike = itemView.findViewById<TextView>(R.id.like)
    var textViewPostBody = itemView.findViewById<TextView>(R.id.postBody)
}