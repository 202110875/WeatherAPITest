package com.example.openapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.openapi.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), DustView{
    lateinit var binding: FragmentHomeBinding
    private var dustDatas: DustResponse.Response? = null


    //이 함수 시도...
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false) //초기화

        //API GET 호출
        val dustService = DustService()
        dustService.setDustView(this)
        dustService.getDusts()

        return binding.root
    }
    private fun initRecyclerView(result: DustResponse.Response) { // Adapter와 Datalist 연결
        val albumRVAdapter = DustRVAdapter(requireContext(), result)
        Log.d("result2", result.toString())
        binding.homeDustInfoRv.adapter = albumRVAdapter
    }

    override fun onGetDustLoading() {
    }

    override fun onGetDustSuccess(code: String, result: DustResponse) {
        Log.d("result", result.response.toString())
        initRecyclerView(result.response)
    }

    override fun onGetDustFailure(code: String, message: String) {

    }
}