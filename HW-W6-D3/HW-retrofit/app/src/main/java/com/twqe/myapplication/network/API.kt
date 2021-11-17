package com.twqe.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    companion object{
        private  val retrofit:Retrofit
        init {
            retrofit= Retrofit.Builder()
                .baseUrl("https://61938d0dd3ae6d0017da866b.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getInstance(): Retrofit {
            return retrofit
        }
    }
}