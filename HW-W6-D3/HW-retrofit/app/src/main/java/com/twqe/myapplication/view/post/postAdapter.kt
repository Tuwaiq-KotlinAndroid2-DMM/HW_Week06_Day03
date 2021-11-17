package com.twqe.myapplication

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.twqe.myapplication.modle.Post


class PostHolder(v: View) : RecyclerView.ViewHolder(v) {
    var imageViewAvatar = v.findViewById<ImageView>(R.id.imageViewAvatar)
    var textViewName = v.findViewById<TextView>(R.id.textViewName)
    var textViewBody = v.findViewById<TextView>(R.id.textViewBody)
    var checkBoxLike = v.findViewById<CheckBox>(R.id.checkBoxLike)
}

class postAdapter(var data: List<Post>) : RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        Picasso.get().load(Uri.parse(data[position].avatar)).into(holder.imageViewAvatar)
        holder.textViewName.text = data[position].name
        holder.textViewBody.text = data[position].postBody
        holder.checkBoxLike.text = data[position].likes.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
