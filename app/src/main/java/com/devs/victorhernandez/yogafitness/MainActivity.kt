package com.devs.victorhernandez.yogafitness

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //evento onclick
        btnExercises.setOnClickListener{
           var intent = Intent(this,ListExercises::class.java)
            startActivity(intent)
        }
        btnSetting.setOnClickListener{
            var intent = Intent(this, SettingsPage::class.java)
            startActivity(intent)
        }
        btnTraining.setOnClickListener {
            var intent = Intent(this, Daily_Training::class.java)
            startActivity(intent)
        }
        btnCalendar.setOnClickListener {
            var intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }
}
