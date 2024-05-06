package com.example.openapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DustService {
    private lateinit var dustView: DustView

    fun setDustView(dustView: DustView){
        this.dustView = dustView
    }

    fun getDusts(){
        val dustService = getRetrofit().create(DustRetrofitInterface::class.java)

        dustView.onGetDustLoading()

        dustService.getDusts().enqueue(object : Callback<DustResponse>{
            override fun onResponse(call: Call<DustResponse>, response: Response<DustResponse>){
                if(response.isSuccessful && response.code() == 200){
                    val dustResponse: DustResponse = response.body()!!

                    Log.d("DUST-RESPONSE", response.body().toString())

                    when(val code = dustResponse.response.header.resultCode){
                        "00" -> {
                            dustView.onGetDustSuccess(code, dustResponse)
                        }
                        else -> dustView.onGetDustFailure(code, dustResponse.response.header.resultMsg)
                    }
                }
            }

            override fun onFailure(p0: Call<DustResponse>, p1: Throwable) {
                dustView.onGetDustFailure("400", "네트워크 오류가 발생하였습니다.")
            }
        })
    }
}