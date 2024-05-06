package com.example.openapi

import retrofit2.Call
import retrofit2.http.GET

interface DustRetrofitInterface {

    //시도별 실시간 측정정보 조회
    @GET("getCtprvnRltmMesureDnsty?sidoName=서울&pageNo=1&numOfRows=100&returnType=json&serviceKey=g7NSkaPo6G2Gk8m43kfou1fdDEWHe2XsOVBpjbn%2B6%2Fq70jv7wx9YGBCxPQU3Oq%2Fl5vl%2B3tkOYH6kDd2oV0K1wg%3D%3D&ver=1.0")
    fun getDusts(): Call<DustResponse> //응답값(AuthResponse) 리스트로 변환

}