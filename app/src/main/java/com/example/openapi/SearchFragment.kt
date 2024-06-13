package com.example.openapi

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.openapi.databinding.FragmentSearchBinding
import java.util.Timer
import kotlin.concurrent.thread
import kotlin.concurrent.timer

class SearchFragment : Fragment(){

    lateinit var binding: FragmentSearchBinding

    var isRunning = false
    var timer : Timer? = null		// timer 변수 생성
    var time = 0			// time 변수 생성

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        //시작버튼
        binding.searchStopwatchStartBt.setOnClickListener {
            if (isRunning) {
                pause()
            } else {
                start()
            }
        }

        //종료버튼
        binding.searchStopwatchResetBt.setOnClickListener {
            refresh()
        }

        return binding.root
    }

    private fun start(){ //시작
        binding.searchStopwatchStartBt.text = "일시정지"
        binding.searchStopwatchStartBt.setBackgroundColor(getResources().getColor(R.color.purple_200))
        isRunning = true

        // 핸들러 객체 생성
        val handler = android.os.Handler(Looper.getMainLooper())

        //스톱워치를 시작
        timer = timer(period = 10) {//10밀리초마다 time++
            time++

            val milli_second = time % 100
            val second = (time % 6000) / 100
            val minute = time / 6000

            thread{
                if (isRunning) {
                    handler.post{
                        // 밀리초
                        binding.searchStopwatchTextMillisecondTv.text = if (milli_second < 10) ":0${milli_second}" else ":${milli_second}"
                        // 초
                        binding.searchStopwatchTextSecondTv.text = if (second < 10) ":0${second}" else ":${second}"
                        // 분
                        binding.searchStopwatchTextMinuteTv.text = if (minute < 10) "0${minute}" else "${minute}"
                    }
                }
            }
        }
    }

    private fun pause(){ //정지
        binding.searchStopwatchStartBt.text = "시작"
        binding.searchStopwatchStartBt.setBackgroundColor(getResources().getColor(R.color.blue))
        isRunning = false
        timer?.cancel()
    }

    private fun refresh(){ //초기화
        binding.searchStopwatchStartBt.text = "시작"
        timer?.cancel()
        time = 0 //time 초기화
        isRunning = false

        binding.searchStopwatchStartBt.setBackgroundColor(getResources().getColor(R.color.blue))

        //타이머 텍스트 초기화
        binding.searchStopwatchTextMillisecondTv.text = ":00"
        binding.searchStopwatchTextSecondTv.text = ":00"
        binding.searchStopwatchTextMinuteTv.text = "00"
    }
}