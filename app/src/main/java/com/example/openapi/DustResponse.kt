package com.example.openapi

data class DustResponse(
    val response: Response
) {
    data class Response(
        val body: Body,
        val header: Header
    ) {
        data class Body(
            val items: List<Item>,
            val numOfRows: Int,
            val pageNo: Int,
            val totalCount: Int
        ) {
            data class Item(
                val coFlag: Any,
                val coGrade: String,
                val coValue: String,
                val dataTime: String,
                val khaiGrade: String,
                val khaiValue: String,
                val no2Flag: Any,
                val no2Grade: String,
                val no2Value: String,
                val o3Flag: Any,
                val o3Grade: String,
                val o3Value: String,
                val pm10Flag: String,
                val pm10Grade: String,
                val pm10Value: String,
                val pm25Flag: String,
                val pm25Grade: String,
                val pm25Value: String,
                val sidoName: String,
                val so2Flag: Any,
                val so2Grade: String,
                val so2Value: String,
                val stationName: String
            )
        }

        data class Header(
            val resultCode: String,
            val resultMsg: String
        )
    }
}