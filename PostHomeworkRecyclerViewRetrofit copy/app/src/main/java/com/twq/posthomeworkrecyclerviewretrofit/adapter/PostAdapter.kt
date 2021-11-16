package com.twq.posthomeworkrecyclerviewretrofit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.twq.posthomeworkrecyclerviewretrofit.R
import com.twq.posthomeworkrecyclerviewretrofit.model.Post
import com.twq.posthomeworkrecyclerviewretrofit.postsServices
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.log

class PostAdapter(var data: MutableList<Post>): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {

        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_row,parent,false)
        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        val currntPost = data[position]
        holder.apply {
            tvAvatarName.text = currntPost.name
            tvLikesNum.text = currntPost.likes.toString()
            tvPostBody.text = currntPost.postBody
            Picasso.get().load(currntPost.avatar).into(imgAvatar)
        }

        holder.imgViewDeleteBtn.setOnClickListener {
        postsServices.deletePost(currntPost.id).enqueue(object : retrofit2.Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.d("UpdateProduct", "Updated successfully")
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("UpdateProduct", "Updated error")

            }

        })
        }

        holder.imgViewUpdateBtn.setOnClickListener {
            postsServices.updatePost(currntPost.id,
                Post("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSL8goG2XQrD1vvyjLZ6WSNCXg6_Cnk4Sb5Vw&usqp=CAU",
                currntPost.id,5000000,"GOAT",
                    "This Post was Updated by Mohammed")
            ).enqueue(
                object : retrofit2.Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        val retrievedProduct = response.body()
                        Log.d("UpdateProduct", "Updated successfully")
                        Log.d("UpdateProduct", retrievedProduct.toString())
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.d("updated", "no: ${t.message}")
                    }

                }
            )
        }

    }

    override fun getItemCount(): Int {
       return data.size
    }

    class PostHolder(v: View):RecyclerView.ViewHolder(v){
        var imgAvatar = v.findViewById<ImageView>(R.id.imageViewAvatar)
        var tvPostBody = v.findViewById<TextView>(R.id.textViewPostBody)
        var tvLikesNum = v.findViewById<TextView>(R.id.textViewLikesNumbers)
        var imgViewLikesBtn = v.findViewById<ImageView>(R.id.imageViewLikeBtn)
        var tvAvatarName = v.findViewById<TextView>(R.id.textViewAvtarName)
        var imgViewDeleteBtn = v.findViewById<ImageView>(R.id.imageViewDelete)
        var imgViewUpdateBtn = v.findViewById<ImageView>(R.id.imageViewUpdate)
    }
}