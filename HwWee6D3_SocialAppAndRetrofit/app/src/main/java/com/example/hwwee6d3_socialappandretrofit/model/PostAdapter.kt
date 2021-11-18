package com.example.hwwee6d3_socialappandretrofit.model

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hwwee6d3_socialappandretrofit.R
import com.squareup.picasso.Picasso

class PostAdapter(var data: List<Post>) :RecyclerView.Adapter<PostHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
       var v = LayoutInflater.from(parent.context).inflate(R.layout.post_list,parent,false)

        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {


        Picasso.get().load(Uri.parse(data[position].avatar)).into(holder.imageViewAvatar)
        holder.textViewPostName.text = data[position].name
        holder.textViewPostBody.text = data[position].postBody
        holder.textViewlikes.text = data[position].likes.toString()


    }

    override fun getItemCount(): Int {
        return data.size
    }
}





class PostHolder (v:View) : RecyclerView.ViewHolder(v){

    var textViewPostName = v.findViewById<TextView>(R.id.textViewPostName)
    var textViewPostBody = v.findViewById<TextView>(R.id.textViewPostBody)
    var imageViewAvatar = v.findViewById<ImageView>(R.id.imageViewAvatar)
    var textViewlikes = v.findViewById<TextView>(R.id.textViewlikes)
}