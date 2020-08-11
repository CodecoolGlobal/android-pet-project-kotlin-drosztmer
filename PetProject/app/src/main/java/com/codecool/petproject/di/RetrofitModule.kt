package com.codecool.petproject.di

import com.codecool.petproject.BASE_URL
import com.codecool.petproject.model.ServiceUtil
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single { okHttp() }

    single { retrofit(BASE_URL) }

    single {
        get<Retrofit>().create(ServiceUtil::class.java)
    }
}

private fun okHttp() = OkHttpClient.Builder()
    .build()

private fun retrofit(baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()