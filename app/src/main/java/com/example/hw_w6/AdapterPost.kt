package com.example.hw_w6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import retrofit2.http.POST

class AdapterPost(var postList: List<POST>): RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var layoutflater=LayoutInflater.from(parent.context).inflate(R.layout.row , parent,false)
        return PostHolder(layoutflater)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        Picasso.get().load(postList[position].avatar).into(holder.avatar)
        holder.name.text = postList[position].name
        holder.likes.text = postList[position].like.toString()
        holder.bodypost.text = postList[position].bodypost.toString()

    }

    override fun getItemCount(): Int {
      return postList.size
    }


}

class PostHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var avatar= itemView.findViewById<ImageView>(R.id.imageView)
    var name= itemView.findViewById<TextView>(R.id.textviewname)
    var likes= itemView.findViewById<TextView>(R.id.textViewlike)
    var bodypost= itemView.findViewById<TextView>(R.id.textViewbody)

}
