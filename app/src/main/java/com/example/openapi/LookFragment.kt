package com.example.openapi

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.openapi.databinding.FragmentLookBinding
import kotlin.concurrent.thread

class LookFragment : Fragment(){

    lateinit var binding: FragmentLookBinding

    // 타이머에서 사용 될 필드변수 선언 및 초기화
    private var total = 30
    private var started = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLookBinding.inflate(inflater, container, false)

        val handler = android.os.Handler(Looper.getMainLooper())

        val imageList = arrayListOf<Int>()

        imageList.add(R.drawable.puppy1)
        imageList.add(R.drawable.puppy2)
        imageList.add(R.drawable.puppy3)
        imageList.add(R.drawable.puppy1)
        imageList.add(R.drawable.puppy2)
        imageList.add(R.drawable.puppy3)
        imageList.add(R.drawable.puppy1)
        imageList.add(R.drawable.puppy2)
        imageList.add(R.drawable.puppy3)

        Thread {
            for (image in imageList) {
                handler.post{
                    binding.lookIv.setImageResource(image)
                }
                Thread.sleep(2000)
            }
        }.start()

        //타이머 실행
        Timer()

        return binding.root
    }

    private fun Timer() { //타이머 기능

        binding.lookTimerTextTv.text = "00:30" //30초 타이머 설정

        // 핸들러 객체 생성
        val handler = android.os.Handler(Looper.getMainLooper())


        // 시작버튼 클릭 - 타이머 시작
        binding.lookTimerStartBt.setOnClickListener {
            started = true
            total = 30

            // 쓰레드 실행 - 타이머를 위한 쓰레드
            thread(start = true) {
                while(started && total != 0){

                    // 1초 지연
                    Thread.sleep(1000)

                    total -= 1 //1초마다 -1 감소
                    val minute = String.format("%02d", total / 60) // 분
                    val second = String.format("%02d", total % 60) // 초
                    handler.post{
                        binding.lookTimerTextTv.text = "$minute:$second"
                    }
                }

                // 타이머 값 0으로 될 때 (종료시) 00:30으로 타이머 다시 초기화
                total = 30
                handler.post{
                    binding.lookTimerTextTv.text = "00:30"
                }
            }
        }

        // 종료 버튼 클릭 - 타이머 종료 및 00:30 초기화
        binding.lookTimerEndBt.setOnClickListener {
            started = false
            total = 30
        }

    }
}