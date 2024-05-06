package com.example.openapi

interface DustView {
    fun onGetDustLoading()

    fun onGetDustSuccess(code : String, result: DustResponse)

    fun onGetDustFailure(code: String, message: String)
}