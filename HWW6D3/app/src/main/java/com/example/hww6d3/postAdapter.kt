package com.example.hww6d3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PostHolder(v: View):RecyclerView.ViewHolder(v) {
    var avatar= v.findViewById<ImageView>(R.id.avatar)
    var name= v.findViewById<TextView>(R.id.name)
    var body= v.findViewById<TextView>(R.id.body)
    var like= v.findViewById<TextView>(R.id.like)
}
class postAdapter(var data: List<Post>) : RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.name.text= data[position].name
        holder.body.text= data[position].postBody
        holder.like.text= data[position].likes.toString()
        Picasso.get().load(data[position].avatar).into(holder.avatar)


    }

    override fun getItemCount(): Int {
        return data.size
    }

}