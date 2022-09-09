package com.asfdfsd.todo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       startapplecation()

    }

    private fun startapplecation() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent :Intent=Intent(this,Home::class.java)
            startActivity(intent)
        },2000)

    }
}