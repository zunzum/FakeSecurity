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
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Timer
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask
import kotlin.math.log
import kotlin.time.Duration.Companion.days
import kotlin.time.DurationUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var insDay = 1666429408278 //차단일시 눌러서 변환할 변수 선언
        val dateFormat = SimpleDateFormat("d일\nhh:mm:ss") //가운데 돌아가는거 시간형식 지정해주기
        val upDateFormat = SimpleDateFormat("yy.MM.dd hh:mm") //차단일시형식 지정해주기

        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate) //animation이라는 변수 생성 후 그 안에 anim에 따로 생성해둔 kt파일 불러오기
        dolgi.startAnimation(animation) //dolgi에 시작애니메이션 넣기

        val cutTime = System.currentTimeMillis() //cuttime에 어플 켠 시간 날것으로 받아오기
        Log.d("켠 시간 날것","$cutTime")
        kotlin.concurrent.timer(period = 1000){
            //1초마다 실행될 블록 백그라운드에서 작업
            val nowTime = System.currentTimeMillis()
            val minusTime = dateFormat.format(Date(nowTime-insDay))
            runOnUiThread {
                //ui상에서 표시될 블록
                chadanTime.text = "$minusTime" //차단타임을 현재시간에서 어플켠시간을 뺀 시간으로 설정
            }
        }

        //메뉴 버튼을 누르면 할 동작
        menuBtn.setOnClickListener {
//            //상수로 myIntent라는 변수명을 설정,여기서출발, 목적지 입력
//            val myIntent = Intent(this,timeEditActivity::class.java)
//            //가서 뭘 받아오는 명령어(위에 설정해놨던 myIntent실행, 뭘 받아올건지 태그)ㄴ
//            startActivityForResult(myIntent, REQUEST_FOR_STARTDATE)
            }
        //비콘버튼 누르면 동작
        biconBtn.setOnClickListener {
            val curTime = upDateFormat.format(Date(cutTime)) //날것의 시간을 date형식으로 받아 형식 지정
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
        //차단일시 누르면 동작
        insDayTxt.setOnClickListener {
            val chadanDay = EditText(this)
            chadanDay.gravity=Gravity.CENTER
            val builder = AlertDialog.Builder(this)
                .setTitle("원하는 일시 입력 0000-00-00")
                .setView(chadanDay)
                .setPositiveButton("확인",
                DialogInterface.OnClickListener { dialogIn, id ->
                    val inputChadanDay = chadanDay.text.toString()+" 07:30:00"  //alert에서 입력 받기
                    var inChaDanDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(inputChadanDay)    //입력받은 inputChadanDay를 지정한 포맷으로 변환
                    var insDay = inChaDanDate.time //전역변수에 할당해주기!! 이거 되는지 모르겠네
                    var ilsi = upDateFormat.format(Date(insDay))

                    insDayTxt.text = "차단일시 : ${ilsi}"

                })
            builder.show()
        }
        }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        //돌아온 이유가 날자를 받으러 온게 맞는지?
//        if (requestCode == REQUEST_FOR_STARTDATE) {
//            //추가질문 : 확인을 눌러서 돌아온게 맞는지?
//            if (resultCode == RESULT_OK) {
//                //실제 첨부된 새 닉네임을 꺼내서 텍스트뷰에 반영
//                val newDate = data?.getStringExtra("chadan")
//                Log.d("입력받아온 시간","$newDate")
//                insDayTxt.text = newDate.toString()
//            }
//        }
//    }
}