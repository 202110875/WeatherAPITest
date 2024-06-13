package com.example.openapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.openapi.databinding.FragmentHomeBinding
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), DustView{
    lateinit var binding: FragmentHomeBinding
    private var dustDatas: DustResponse.Response? = null
    private lateinit var shimmerViewContainer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false) //초기화

        //API GET 호출
        val dustService = DustService()

        //Shimmer Effect
        lifecycleScope.launch {
            showSampleData(isLoading = true)
            delay(3000)
            showSampleData(isLoading = false)
        }

        //Data 가져오기
        dustService.setDustView(this)
        dustService.getDusts()

        return binding.root
    }
    private fun initRecyclerView(result: DustResponse) { // Adapter와 Datalist 연결
        val albumRVAdapter = DustRVAdapter(requireContext(), result)
        Log.d("result2", result.toString())
        binding.homeDustInfoRv.adapter = albumRVAdapter
        binding.homeDustInfoRv.layoutManager = GridLayoutManager(requireContext(), 1)
    }

    override fun onGetDustLoading() {
    }

    override fun onGetDustSuccess(code: String, result: DustResponse) {
        Log.d("result", result.response.toString())
        initRecyclerView(result)
    }

    override fun onGetDustFailure(code: String, message: String) {

    }

    private fun showSampleData(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmerViewContainer.startShimmer()
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.homeDustInfoRv.visibility = View.GONE
        } else {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            binding.homeDustInfoRv.visibility = View.VISIBLE
        }
    }
}