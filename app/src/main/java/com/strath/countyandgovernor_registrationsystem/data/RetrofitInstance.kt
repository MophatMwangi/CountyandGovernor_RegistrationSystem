package com.strath.countyandgovernor_registrationsystem.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object
    {
        val BASE_URL = "https://edfafarmer.appspot.com/"
        fun getRetrofitInstance(): Retrofit
        {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}