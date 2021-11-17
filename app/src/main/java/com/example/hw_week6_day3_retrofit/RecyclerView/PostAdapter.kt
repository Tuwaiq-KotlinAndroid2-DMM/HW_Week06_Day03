package com.example.hw_week6_day3_retrofit.RecyclerView

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_week6_day3_retrofit.Model.Post
import com.example.hw_week6_day3_retrofit.PostDeta
import com.example.hw_week6_day3_retrofit.R
import com.squareup.picasso.Picasso

class PostAdapter(var data :List<Post> ):RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
    val view =    LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false)
        return PostHolder(view)
   }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        var context = holder.cl.context
        holder.cl.setOnClickListener {

            var i = Intent(context, PostDeta::class.java)
            i.putExtra("post", data[position])
            context.startActivity(i)

        }
        holder.userName.text=data[position].name
        holder.postBody.text=data[position].postBody

        Picasso.get().load(data[position].avatar).into(holder.avatar)
    }

    override fun getItemCount(): Int {
        return data.size   }
}

class PostHolder(v:View):RecyclerView.ViewHolder(v){

    var avatar =v.findViewById<ImageView>(R.id.avatar)
    var userName =v.findViewById<TextView>(R.id.name)
    var postBody =v.findViewById<TextView>(R.id.postBody)
    var cl =v.findViewById<ConstraintLayout>(R.id.constLayo)



}