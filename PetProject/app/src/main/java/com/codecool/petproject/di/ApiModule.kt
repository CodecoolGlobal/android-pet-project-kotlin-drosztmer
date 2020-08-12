package com.codecool.petproject.di

import com.codecool.petproject.BASE_URL
import com.codecool.petproject.model.CharactersApi
import com.codecool.petproject.model.CharactersService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideCharactersApi(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }

    @Provides
    fun provideCharactersService(): CharactersService {
        return CharactersService()
    }
}