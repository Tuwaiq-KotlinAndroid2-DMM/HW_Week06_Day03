package com.twq.homework.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twq.homework.R
import java.net.URL


class Adapter (var data: List<Post>):RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_post,parent,false)
        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.username.text = data[position].name
        holder.body.text = data[position].postBody
        holder.likes.text = data[position].likes.toString()
        Picasso.get().load(data[position].avatar).into(holder.img)


    }

    override fun getItemCount(): Int {
        return data.size

    }
}
class PostHolder(v: View):RecyclerView.ViewHolder(v){
    var username = v.findViewById<TextView>(R.id.textViewName)
    var body = v.findViewById<TextView>(R.id.textViewBody)
    var likes = v.findViewById<TextView>(R.id.textViewLikes)
    var img = v.findViewById<ImageView>(R.id.imageView)

}