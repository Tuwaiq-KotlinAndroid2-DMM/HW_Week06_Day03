package com.example.hw_week6_day3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class postHolder(v: View) : RecyclerView.ViewHolder(v){
    var textViewName=v.findViewById<TextView>(R.id.textViewName)
    var textViewPost=v.findViewById<TextView>(R.id.textViewPost)
    var imageViewAvatar=v.findViewById<ImageView>(R.id.imageViewAvatar)
    var imageViewLike=v.findViewById<ImageView>(R.id.imageViewLike)
}

class postAdapter(var data: List<Post>) : RecyclerView.Adapter<postHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.post_cardview, parent, false)
        return postHolder(v)
    }


    override fun onBindViewHolder(holder: postHolder, position: Int) {
        Picasso.get().load(data[position].avatar).into(holder.imageViewAvatar);

        holder.textViewName.text=data[position].name
        holder.textViewPost.text = data[position].postBody



    }

    override fun getItemCount(): Int {
        return data.size
    }


}
