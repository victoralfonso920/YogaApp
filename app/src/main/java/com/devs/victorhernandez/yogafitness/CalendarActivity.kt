package com.devs.victorhernandez.yogafitness

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.devs.victorhernandez.yogafitness.Api.Globales
import com.devs.victorhernandez.yogafitness.Custom.WorkoutDoneDecorator
import com.devs.victorhernandez.yogafitness.Realm.FuncionesRealm
import com.devs.victorhernandez.yogafitness.Realm.WorkoutDays
import com.devs.victorhernandez.yogafitness.Realm.YogaDB
import com.prolificinteractive.materialcalendarview.CalendarDay
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*

/**
 * Created by victorhernandez on 28/11/17.
 */
class CalendarActivity : AppCompatActivity() {
    var contexto: Context? = null
    var funciones: Globales? = null
    var activityHeigth = 0
    var activityWidth = 0
    internal var realm: Realm? = null
    var homes: RealmResults<YogaDB>? = null
    internal var misFuncionesRealm: FuncionesRealm? = null

    val list: HashSet<CalendarDay>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
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

        val  workoutdays: RealmResults<WorkoutDays> = misFuncionesRealm!!.recuperadatosWork(realm)
        val convertedList : HashSet<CalendarDay> = HashSet()
        for(i in workoutdays){
            convertedList.add(CalendarDay.from(Date(i.day.toLong())))
            calendar.addDecorator(WorkoutDoneDecorator(convertedList))
        }

    }
}
