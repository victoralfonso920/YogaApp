package com.devs.victorhernandez.yogafitness

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.devs.victorhernandez.yogafitness.Adarters.RecyclerViewAdapter
import com.devs.victorhernandez.yogafitness.Model.Exercise
import kotlinx.android.synthetic.main.activity_list_exercises.*

/**
 * Created by victorhernandez on 10/9/17.
 */
class ListExercises : AppCompatActivity() {

    var exerciselist :ArrayList<Exercise>? = null
    var layoutmanager:RecyclerView.LayoutManager? = null
    var recyclerview:RecyclerView? = null
    var adapter: RecyclerViewAdapter? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_exercises)

        initData()
        adapter = RecyclerViewAdapter(exerciselist!!,baseContext)
        layoutmanager = LinearLayoutManager(this)
        list_ex.layoutManager = layoutmanager
        list_ex.adapter = adapter
    }

    private fun initData() {
        exerciselist = ArrayList<Exercise>()
        exerciselist!!.add(Exercise(R.drawable.easy_pose,"Easy pose"))
        exerciselist!!.add(Exercise(R.drawable.cobra_pose,"Cobra pose"))
        exerciselist!!.add(Exercise(R.drawable.downward_facing_dog,"Downward facing dog"))
        exerciselist!!.add(Exercise(R.drawable.boat_pose,"Boat pose"))
        exerciselist!!.add(Exercise(R.drawable.half_pigeon,"Half pigeon"))
        exerciselist!!.add(Exercise(R.drawable.low_lunge,"Low lunge"))
        exerciselist!!.add(Exercise(R.drawable.upward_bow,"Upward bow"))
        exerciselist!!.add(Exercise(R.drawable.crescent_lunge,"Crescent lunge"))
        exerciselist!!.add(Exercise(R.drawable.warrior_pose,"Warrior pose"))
        exerciselist!!.add(Exercise(R.drawable.bow_pose,"Bow pose"))
        exerciselist!!.add(Exercise(R.drawable.warrior_pose_2,"Warrior pose 2"))


    }
}

