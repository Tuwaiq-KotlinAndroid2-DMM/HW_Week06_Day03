package com.example.w6_d3_homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.w6_d3_homework.model.Post
import com.example.w6_d3_homework.network.PostService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostAdapter(var data : List<Post>):RecyclerView.Adapter<PostHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_row_post, parent, false)
        return PostHolder(v)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.nameTv.text=data[position].name
        holder.bodyTv.text=data[position].postBody
        holder.likesTv.text=data[position].likes.toString()
        Picasso.get().load(data[position]?.avatar!!).into(holder.avatarImgView)

        var retorfit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var postService = retorfit.create(PostService::class.java)

        holder.btnDelete.setOnClickListener {
            postService.deleteProduct(Integer.valueOf(data[position]?.id)).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    Toast.makeText(holder.avatarImgView.context, "delete successfully", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class PostHolder(v: View): RecyclerView.ViewHolder(v){
    var nameTv = v.findViewById<TextView>(R.id.textViewName)
    var bodyTv = v.findViewById<TextView>(R.id.textViewPostBody)
    var likesTv = v.findViewById<TextView>(R.id.textViewLikeNum)
    var avatarImgView = v.findViewById<ImageView>(R.id.imageViewAvatar)
    var btnDelete = v.findViewById<Button>(R.id.buttonDelete)
}