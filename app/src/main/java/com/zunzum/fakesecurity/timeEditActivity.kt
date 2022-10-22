package com.zunzum.fakesecurity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_time_edit.*

class timeEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_edit)

        okBtn.setOnClickListener {
            //입력한 날자를 변수로 저장
            val inputChadanDate = chadanSetTime.text.toString()

            //입력한 닉네임을 가지고 메인으로 복귀

            //입력한 닉네임을 담아주기 윈한 용도로만 사용하는 Intent
            val resultIntent = Intent()
            resultIntent.putExtra("chadan", inputChadanDate)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        noBtn.setOnClickListener {
            finish()
        }
    }
}