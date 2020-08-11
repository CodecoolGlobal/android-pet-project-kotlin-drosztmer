package com.codecool.petproject.model

import io.reactivex.Single
import retrofit2.http.GET

interface BaseApi {

    @GET("all.json")
    fun getData(): Single<List<Character>>

}