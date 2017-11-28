package com.devs.victorhernandez.yogafitness

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.devs.victorhernandez.yogafitness.Api.AlarmNotificationReciver
import com.devs.victorhernandez.yogafitness.Api.Globales
import com.devs.victorhernandez.yogafitness.Realm.FuncionesRealm
import com.devs.victorhernandez.yogafitness.Realm.YogaDB
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

class SettingsPage : AppCompatActivity() {
    internal var misFuncionesRealm: FuncionesRealm? = null
    var contexto: Context? = null
    var funciones: Globales? = null
    var activityHeigth = 0
    var activityWidth = 0
    internal var realm: Realm? = null
    var mode:Int? = null
    var homes: RealmResults<YogaDB>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        //obtener contexto
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
        if(homes!!.size>0){
            mode = homes!!.first().mode
        }else{
            mode = homes!!.size
        }
        setRadioButton(mode!!)

        btnSave.setOnClickListener {
            saveWorkoutMode()
            saveAlarm(switchAlarm.isChecked)
            Toast.makeText(contexto,"SAVED",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun  saveAlarm(checked: Boolean) {
        if(checked){
            val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent: Intent = Intent(contexto,AlarmNotificationReciver::class.java)
            val pendingintent:PendingIntent = PendingIntent.getBroadcast(this,0,intent,0)

            val calendar:Calendar = Calendar.getInstance()
            val today:Date = Calendar.getInstance().time
            calendar.set(today.year,today.month,today.day,timePicker.currentHour,timePicker.currentMinute)

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingintent)

            Log.d("alarma","programada a las "+timePicker.currentHour+":"+timePicker.currentMinute)
        }else{
            val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent: Intent = Intent(contexto,AlarmNotificationReciver::class.java)
            val pendingintent:PendingIntent = PendingIntent.getBroadcast(this,0,intent,0)

            manager.cancel(pendingintent)
        }
    }

    private fun saveWorkoutMode() {
        val selectedID = rdiGroup.checkedRadioButtonId
        when(selectedID){
            R.id.rdiEasy->misFuncionesRealm!!.EstablecerModo(realm,0)
            R.id.rdiMedium->misFuncionesRealm!!.EstablecerModo(realm,1)
            R.id.rdiHard->misFuncionesRealm!!.EstablecerModo(realm,2)
        }
    }

    fun setRadioButton(mode:Int){
        when(mode){
            0 -> rdiGroup.check(R.id.rdiEasy)
            1 -> rdiGroup.check(R.id.rdiMedium)
            2 -> rdiGroup.check(R.id.rdiHard)
        }
    }

}
