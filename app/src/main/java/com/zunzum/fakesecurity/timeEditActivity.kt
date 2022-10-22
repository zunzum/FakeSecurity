package com.zunzum.fakesecurity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_time_edit.*
import java.text.SimpleDateFormat

class timeEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_edit)

        okBtn.setOnClickListener {
            finish()
        }
        noBtn.setOnClickListener {
            finish()
        }
    }
}