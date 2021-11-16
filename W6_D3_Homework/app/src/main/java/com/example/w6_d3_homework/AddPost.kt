package com.example.w6_d3_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.w6_d3_homework.model.Post
import com.example.w6_d3_homework.network.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddPost : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        var editTextName = findViewById<EditText>(R.id.editTextName).text
        var editTextPostBody = findViewById<EditText>(R.id.editTextBody).text
        var avatarURL =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-kEMUrjjp-dl3Y1q5b-lNC_m10w_ta96cJA&usqp=CAU"
        var btnAdd = findViewById<Button>(R.id.buttonAdd)
        var retorfit = Retrofit.Builder()
            .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var postService = retorfit.create(PostService::class.java)
        btnAdd.setOnClickListener {
            var name = editTextName.toString()
            var postBody = editTextPostBody.toString()

            postService.addPost(Post(avatarURL, "65", 0, name, postBody))
                .enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        if(response.isSuccessful){
                            Toast.makeText(this@AddPost, "Post added", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {

                    }
                })
        }

    }
}