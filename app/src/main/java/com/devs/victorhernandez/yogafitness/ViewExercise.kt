package com.devs.victorhernandez.yogafitness

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.devs.victorhernandez.yogafitness.Api.Common
import com.devs.victorhernandez.yogafitness.Api.Globales
import com.devs.victorhernandez.yogafitness.Realm.FuncionesRealm
import com.devs.victorhernandez.yogafitness.Realm.YogaDB
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_view_exercise.*
import java.util.concurrent.TimeUnit

class ViewExercise : AppCompatActivity() {
    var image_id: Int? = null
    var name: String? = null
    var time: CountDownTimer? = null
    var contexto: Context? = null
    var isrunning: Boolean = false
    var milliLeft:Long? = null

    internal var misFuncionesRealm: FuncionesRealm? = null
    var funciones: Globales? = null
    var activityHeigth = 0
    var activityWidth = 0
    internal var realm: Realm? = null
    var tiempo:Int = 0
    var homes: RealmResults<YogaDB>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_exercise)
        contexto = this
        timer.text = ""

        //inizializar libs
        try {
            activityHeigth = Globales.Display("h", this)
            activityWidth = Globales.Display("w", this)
            funciones = Globales(activityWidth, activityHeigth)
            realm = funciones!!.initrealm(this, realm)
            misFuncionesRealm = FuncionesRealm()
        } catch (e: Exception) {
        }
        homes = misFuncionesRealm!!.recuperadatos(realm)
        if(homes!!.size > 0){
            when(homes!!.first().mode){
                0-> tiempo = Common.TIME_LIMIT_EASY
                1-> tiempo = Common.TIME_LIMIT_MEDIUM
                2-> tiempo = Common.TIME_LIMIT_HARD
            }
        }else{
            tiempo = Common.TIME_LIMIT_EASY
        }



        btnStar.setOnClickListener {
            if(!isrunning){
                btnStar.text = "DONE"
                timerStart(tiempo)
            }else{
                Toast.makeText(contexto, "Finish!!!", Toast.LENGTH_SHORT).show()
                finish()
            }
            isrunning = !isrunning
        }
        if (intent != null) {
            image_id = intent.getIntExtra("image_id", -1)
            name = intent.getStringExtra("name")
            detail_image.setImageResource(image_id!!)
            tittle.text = name!!
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(time != null){
            time!!.cancel()
            time = null
        }
    }

    override fun onPause() {
        super.onPause()
        if(time != null){
            time!!.cancel()
        }
    }

    override fun onResume() {
        super.onResume()
        if(time != null && isrunning){
            timerStart(milliLeft!!.toInt())
        }
    }
     fun timerStart(timeLengthMilli:Int ){
        time = object : CountDownTimer(timeLengthMilli.toLong(), 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished:Long) {
                milliLeft=millisUntilFinished
                timer.text = "" + String.format(""+TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))
            }

            override fun onFinish() {
                Toast.makeText(contexto, "Finish!!!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.start()
    }
}
