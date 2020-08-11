package com.codecool.petproject.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL: String = "https://cdn.rawgit.com/akabab/starwars-api/0.2.1/api/"

class ApiService(val apiService: ApiService?) {

    fun getBaseApi(): BaseApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(BaseApi::class.java)
    }



}