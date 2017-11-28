package com.devs.victorhernandez.yogafitness

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.devs.victorhernandez.yogafitness.Api.Common
import com.devs.victorhernandez.yogafitness.Api.Globales
import com.devs.victorhernandez.yogafitness.Model.Exercise
import com.devs.victorhernandez.yogafitness.Realm.FuncionesRealm
import com.devs.victorhernandez.yogafitness.Realm.YogaDB
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_daily__training.*
import java.util.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

class Daily_Training : AppCompatActivity() {

    var ex_id = 0
    val limit_time = 0
    var list: ArrayList<Exercise>? = null
    var time: CountDownTimer? = null
    var timerexercises: CountDownTimer? = null
    var resttimerexercises: CountDownTimer? = null
    var contexto: Context? = null
    var funciones: Globales? = null
    var activityHeigth = 0
    var activityWidth = 0
    internal var realm: Realm? = null

    var homes: RealmResults<YogaDB>? = null
    internal var misFuncionesRealm: FuncionesRealm? = null
    var tiempo: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily__training)
        contexto = this
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
        if (homes!!.size > 0) {
            when (homes!!.first().mode) {
                0 -> tiempo = Common.TIME_LIMIT_EASY
                1 -> tiempo = Common.TIME_LIMIT_MEDIUM
                2 -> tiempo = Common.TIME_LIMIT_HARD
            }
        } else {
            tiempo = Common.TIME_LIMIT_EASY
        }
        initData()
        //set dta
        progressBar.max = list!!.size

        setExerciseInformation(ex_id)
    }

    private fun setExerciseInformation(id: Int) {
        detail_image_day.setImageResource(list!![id].image_id)
        tittle_day.text = list!![id].name
        btnStar_day.text = "Start"

        detail_image_day.visibility = View.VISIBLE
        btnStar_day.visibility = View.VISIBLE
        timer_dat.visibility = View.VISIBLE

        layout_get_ready.visibility = View.INVISIBLE

        btnStar_day.setOnClickListener {
            if (btnStar_day.text.toString().toLowerCase().equals("start", true)) {
                showGetReady()
                btnStar_day.text = "done"
            } else if (btnStar_day.text.toString().toLowerCase().equals("done", true)) {

                timerexercises!!.cancel()

                if(resttimerexercises != null){
                    resttimerexercises!!.cancel()
                }
                if (ex_id < list!!.size) {
                    showRestTime()
                    ex_id++
                    progressBar.progress = ex_id
                    timer_dat.text = ""
                }else{
                    showFinished()
                }

            }else{
                timerexercises!!.cancel()
                if(resttimerexercises != null){
                    resttimerexercises!!.cancel()
                }
                if(ex_id < list!!.size){
                    setExerciseInformation(ex_id)
                }else{
                    showFinished()
                }
            }
        }

    }

    private fun showRestTime() {
        detail_image_day.visibility = View.INVISIBLE
        timer_dat.visibility = View.INVISIBLE
        btnStar_day.text = "Skip"
        btnStar_day.visibility = View.VISIBLE
        layout_get_ready.visibility = View.VISIBLE
        timerrest()

        txtGetReady.text = "REST TIME"

    }

    private fun showFinished() {
        detail_image_day.visibility = View.INVISIBLE
        btnStar_day.visibility = View.INVISIBLE
        timer_dat.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE

        layout_get_ready.visibility = View.VISIBLE
        txtGetReady.text = "FINISHED !!!!"
        txtCountdown.text = "Congratulation ! \nYou're done with today exercises"
        txtCountdown.textSize = 20f
        misFuncionesRealm!!.EstablecerDias(realm, Calendar.getInstance().timeInMillis.toString())

    }

    private fun showGetReady() {
        detail_image_day.visibility = View.INVISIBLE
        btnStar_day.visibility = View.INVISIBLE
        timer_dat.visibility = View.VISIBLE

        layout_get_ready.visibility = View.VISIBLE
        txtGetReady.text = "GET READY"
        timerStart(6000)
    }

    fun showExercises() {
        if (ex_id < list!!.size ) {
            detail_image_day.visibility = View.VISIBLE
            btnStar_day.visibility = View.VISIBLE
            layout_get_ready.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE

            timer()
            detail_image_day.setImageResource(list!![ex_id].image_id)
            tittle_day.text = list!![ex_id].name
        } else {
            showFinished()
        }

    }


    private fun initData() {
        list = ArrayList<Exercise>()
        list!!.add(Exercise(R.drawable.easy_pose, "Easy pose"))
        list!!.add(Exercise(R.drawable.cobra_pose, "Cobra pose"))
        list!!.add(Exercise(R.drawable.downward_facing_dog, "Downward facing dog"))
        list!!.add(Exercise(R.drawable.boat_pose, "Boat pose"))
        list!!.add(Exercise(R.drawable.half_pigeon, "Half pigeon"))
        list!!.add(Exercise(R.drawable.low_lunge, "Low lunge"))
        list!!.add(Exercise(R.drawable.upward_bow, "Upward bow"))
        list!!.add(Exercise(R.drawable.crescent_lunge, "Crescent lunge"))
        list!!.add(Exercise(R.drawable.warrior_pose, "Warrior pose"))
        list!!.add(Exercise(R.drawable.bow_pose, "Bow pose"))
        list!!.add(Exercise(R.drawable.warrior_pose_2, "Warrior pose 2"))


    }

    fun timerStart(timeLengthMilli: Int) {
        time = object : CountDownTimer(timeLengthMilli.toLong(), 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                txtCountdown.text = "" + String.format("" + TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))
            }

            override fun onFinish() {
                showExercises()
            }
        }.start()
    }

    fun timer() {
        timerexercises = object : CountDownTimer(tiempo.toLong(), 1000) {
            override fun onFinish() {
                if (ex_id < list!!.size -1) {
                    ex_id++
                    progressBar.progress = ex_id
                    timer_dat.text = ""

                    setExerciseInformation(ex_id)
                    btnStar_day.text = "Start"
                }else{
                    showFinished()
                }

            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                timer_dat.text = "" + String.format("" + TimeUnit.MILLISECONDS.toSeconds(p0))
            }

        }.start()
    }

    fun timerrest() {
        resttimerexercises = object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                setExerciseInformation(ex_id)
                showExercises()

            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                txtCountdown.text = "" + String.format("" + TimeUnit.MILLISECONDS.toSeconds(p0))
            }

        }.start()
    }


}
