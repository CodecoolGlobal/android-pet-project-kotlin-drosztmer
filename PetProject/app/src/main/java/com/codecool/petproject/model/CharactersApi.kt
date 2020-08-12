package com.codecool.petproject.model

import io.reactivex.Single
import retrofit2.http.GET

interface CharactersApi {

    @GET("api/all.json")
    fun getCharacters(): Single<List<Character>>

}