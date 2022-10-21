package com.zunzum.fakesecurity

import android.icu.util.TimeUnit
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        dolgi.startAnimation(animation)

        val cutTime = System.currentTimeMillis() //time에 어플 켠 시간 날것으로 받아오기
        val dateFormat = SimpleDateFormat("yy.MM.dd hh:mm") //형식 지정해주기
        val curTime = dateFormat.format(Date(cutTime)) //날것의 시간을 date형식으로 받아 형식 지정
        insDayTxt.text = "차단일시 : $curTime"

        kotlin.concurrent.timer(period = 1000){
            //1초마다 실행될 블록 백그라운드에서 작업
            val nowTime = System.currentTimeMillis()
            val dateFormat = SimpleDateFormat("d일\nhh:mm:ss") //형식 지정해주기
            val minusTime = dateFormat.format(Date(nowTime-cutTime))
            runOnUiThread {
                //ui상에서 표시될 블록
                chadanTime.text = "$minusTime" //차단타임을 현재시간에서 어플켠시간을 뺀 시간으로 설정
            }
        }

        //비콘 버튼을 누르면 할 동작
        biconBtn.setOnClickListener {

            }
        }
}