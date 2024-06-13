package com.example.openapi

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openapi.databinding.ItemDustBinding

class DustRVAdapter(val context: Context, val result: DustResponse): RecyclerView.Adapter<DustRVAdapter.ViewHolder>()  {
    private val dustList = ArrayList<DustResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RESULT", "qwqe")
        //itemview 객체 생성
        val binding: ItemDustBinding = ItemDustBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DustRVAdapter.ViewHolder, position: Int) {
        if(result.response.body.items[position].stationName == "" || result.response.body.items[position].stationName == null){

        }else{
            Log.d("stationName", result.response.body.items[position].stationName)
            holder.stationName.text = result.response.body.items[position].stationName
        }
        holder.sidoName.text = result.response.body.items[position].sidoName
        holder.dataTime.text = result.response.body.items[position].dataTime
        holder.pm10Value.text = result.response.body.items[position].pm10Value
        holder.pm25Value.text = result.response.body.items[position].pm25Value
        holder.so2Value.text = result.response.body.items[position].so2Value
    }

    override fun getItemCount(): Int = result.response.body.items.size

    inner class ViewHolder(val binding: ItemDustBinding): RecyclerView.ViewHolder(binding.root){
        val stationName : TextView = binding.itemHomeDustStationnameTv
        val sidoName : TextView = binding.itemHomeDustSidonameTv
        val dataTime : TextView = binding.itemHomeDustDatetimeTv
        val pm10Value : TextView = binding.itemHomeDustPm10valueTv
        val pm25Value : TextView = binding.itemHomeDustPm25valueTv
        val so2Value : TextView = binding.itemHomeDustSo2valueTv
    }
}