package com.example.retrofithw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdaptePost(var postList: List<post>) : RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var layoutflater=LayoutInflater.from(parent.context).inflate(R.layout.list_post , parent,false)
        return PostHolder(layoutflater)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        Picasso.get().load(postList[position].avatar).into(holder.avatar)
        holder.name.text = postList[position].name
        holder.likes.text = postList[position].likes.toString()
        holder.postBody.text = postList[position].postBody.toString()

    }

    override fun getItemCount(): Int {
     return  postList.size
    }

}


class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var avatar = itemView.findViewById<ImageView>(R.id.imageView)
    var name = itemView.findViewById<TextView>(R.id.textViewName)
    var likes = itemView.findViewById<TextView>(R.id.textViewlike)
    var postBody = itemView.findViewById<TextView>(R.id.textViewpostBody)
}