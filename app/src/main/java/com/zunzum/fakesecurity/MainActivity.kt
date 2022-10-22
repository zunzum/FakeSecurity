package com.zunzum.fakesecurity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.icu.util.TimeUnit
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.math.log
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit

class MainActivity : AppCompatActivity() {

    val REQUEST_FOR_STARTDATE = 1004 //날자를 가지러 가는 인텐트 멤버변수 설정
    var insDay = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate) //animation이라는 변수 생성 후 그 안에 anim에 따로 생성해둔 kt파일 불러오기
        dolgi.startAnimation(animation) //dolgi에 시작애니메이션 넣기

        val cutTime = System.currentTimeMillis() //cuttime에 어플 켠 시간 날것으로 받아오기
        Log.d("켠 시간 날것","$cutTime")
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

        //메뉴 버튼을 누르면 할 동작
        menuBtn.setOnClickListener {
            //상수로 myIntent라는 변수명을 설정,여기서출발, 목적지 입력
            val myIntent = Intent(this,timeEditActivity::class.java)
            //가서 뭘 받아오는 명령어(위에 설정해놨던 myIntent실행, 뭘 받아올건지 태그)ㄴ
            startActivityForResult(myIntent, REQUEST_FOR_STARTDATE)
            }
        //비콘버튼 누르면 동작
        biconBtn.setOnClickListener {
            val dateFormat = SimpleDateFormat("yy.MM.dd hh:mm") //형식 지정해주기
            val curTime = dateFormat.format(Date(cutTime)) //날것의 시간을 date형식으로 받아 형식 지정

            val alert = AlertDialog.Builder(this)
            alert.setTitle("설정하시겠습니까?")
                .setMessage("확인을 누르면 차단일시를 현재시간으로 설정")
                .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialog, id ->
                    insDayTxt.text = "차단일시 : $curTime"
                })
                .setNegativeButton("취소",null)
            alert.show()
        }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //돌아온 이유가 날자를 받으러 온게 맞는지?
        if (requestCode == REQUEST_FOR_STARTDATE) {
            //추가질문 : 확인을 눌러서 돌아온게 맞는지?
            if (resultCode == RESULT_OK) {
                //실제 첨부된 새 닉네임을 꺼내서 텍스트뷰에 반영
                val newDate = data?.getStringExtra("chadan")
                insDay = 2022  //insDay는 맨 앞에 설정해둔 변수. 현재시간과 비교할 예정
            }
        }
    }
}