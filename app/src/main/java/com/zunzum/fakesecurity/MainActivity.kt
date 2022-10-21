package com.zunzum.fakesecurity

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

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        dolgi.startAnimation(animation)

        //비콘 버튼을 누르면 할 동작
        biconBtn.setOnClickListener {
            val cutTime = System.currentTimeMillis() //time에 현재 시간 날것으로 받아오기
            val dateFormat = SimpleDateFormat("yy.MM.dd hh:mm") //형식 지정해주기
            val curTime = dateFormat.format(Date(cutTime)) //날것의 시간을 date형식으로 받아 형식 지정
            insDayTxt.text = "차단일시 : $curTime"


            }
        }

}