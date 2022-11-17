package com.zunzum.fakesecurity

import android.app.Activity
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
import androidx.core.app.ActivityCompat
import androidx.core.os.postDelayed
import com.bumptech.glide.Glide
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

        Glide.with(this).load(R.raw.circle).into(dolgi)
        var insDay = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())) //어플 켠 시간 중 연월일까지만 받아오기
        Log.d("확인 어플 켠 시간 연월일","$insDay")
        var inchant = "$insDay 07:30:00" //연월일 + 시간 추가
        Log.d("확인 연월일 시간추가","$inchant")
        var inchantDay = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(inchant) //시간까지 포함하여 변환
        Log.d("확인 시간까지 포함해서 변환","$inchantDay")
        var inDay = inchantDay.time //밀리세컨으로 변환
        Log.d("확인 밀리세컨으로 변환","$inDay")

        val dateFormat = SimpleDateFormat("d일\nHH:mm:ss") //가운데 돌아가는거 시간형식 지정해주기
        val upDateFormat = SimpleDateFormat("yy.MM.dd HH:mm") //차단일시형식 지정해주기
        var chadan = upDateFormat.format(Date(inDay))
        insDayTxt.text = "차단일시 : ${chadan}"


//        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate) //animation이라는 변수 생성 후 그 안에 anim에 따로 생성해둔 kt파일 불러오기
//        dolgi.startAnimation(animation) //dolgi에 시작애니메이션 넣기

        kotlin.concurrent.timer(period = 1000){
            //1초마다 실행될 블록 백그라운드에서 작업
            val nowTime = Calendar.getInstance().timeInMillis
            val chadanDate = nowTime-inDay
            val chadanDay = chadanDate/(24*60*60*1000)
            val chadanHour = "%02d".format(chadanDate/(60*60*1000))
            val chadanMin = "%02d".format((chadanDate%(60*60*1000))/(60*1000))
            val chadanSec = "%02d".format((chadanDate%(60*1000))/1000)
//            val minusTime = dateFormat.format(Date(nowTime-inDay))
//            Log.d("현재시간", "${upDateFormat.format(Date(nowTime))} / $nowTime")
//            Log.d("기준시간", "${upDateFormat.format(Date(inDay))} / $inDay")
//            Log.d("시간차이", "${upDateFormat.format(Date(nowTime-inDay))} / ${nowTime-inDay}")
            runOnUiThread {
                //ui상에서 표시될 블록
                chadanTime.text = "${chadanDay}일\n${chadanHour}:${chadanMin}:${chadanSec}" //차단타임을 현재시간에서 어플켠시간을 뺀 시간으로 설정
            }
        }

        //메뉴 버튼을 누르면 할 동작
        menuBtn.setOnClickListener {
            val timeEditIntent = Intent(this,timeEditActivity::class.java)
            startActivity(timeEditIntent)
            }
        //비콘버튼 누르면 동작
        biconBtn.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            System.runFinalization()
            System.exit(0)
//            val curTime = upDateFormat.format(Date(cutTime)) //날것의 시간을 date형식으로 받아 형식 지정
//            val alert = AlertDialog.Builder(this)
//            alert.setTitle("설정하시겠습니까?")
//                .setMessage("확인을 누르면 차단일시를 현재시간으로 설정")
//                .setPositiveButton("확인",
//                DialogInterface.OnClickListener { dialog, id ->
//                    insDayTxt.text = "차단일시 : $curTime"
//                })
//                .setNegativeButton("취소",null)
//            alert.show()
        }
        //차단일시 누르면 동작
        insDayTxt.setOnClickListener {
//            val chadanDay = EditText(this)
//            chadanDay.gravity=Gravity.CENTER
//            val builder = AlertDialog.Builder(this)
//                .setTitle("원하는 일시 입력 0000-00-00")
//                .setView(chadanDay)
//                .setPositiveButton("확인",
//                DialogInterface.OnClickListener { dialogIn, id ->
//                    val inputChadanDay = chadanDay.text.toString()+" 07:30:00"  //alert에서 입력 받기
//                    var inChaDanDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(inputChadanDay)    //입력받은 inputChadanDay를 지정한 포맷으로 변환
//                    var insDay = inChaDanDate.time //전역변수에 할당해주기!! 이거 되는지 모르겠네
//                    var ilsi = upDateFormat.format(Date(insDay))
//
//                    insDayTxt.text = "차단일시 : ${ilsi}"
//
//                })
//            builder.show()
        }
        }
}