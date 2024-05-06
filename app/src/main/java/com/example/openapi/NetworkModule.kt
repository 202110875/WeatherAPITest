package com.example.openapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/"

fun getRetrofit(): Retrofit{ // Retrofit 객체 생성
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}