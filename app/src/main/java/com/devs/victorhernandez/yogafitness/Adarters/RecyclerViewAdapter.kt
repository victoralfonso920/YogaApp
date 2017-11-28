package com.devs.victorhernandez.yogafitness.Adarters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.devs.victorhernandez.yogafitness.Interface.ItemClickListener
import com.devs.victorhernandez.yogafitness.Model.Exercise
import com.devs.victorhernandez.yogafitness.R
import com.devs.victorhernandez.yogafitness.ViewExercise

/**
 * Created by victorhernandez on 10/9/17.
 */
class RecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    //variables
    var imagen: ImageView? = null
    var text: TextView? = null
    var carta: CardView? = null
    //
    private var itemClickListener: ItemClickListener? = null

    init {
        itemView!!.setOnClickListener(this)
        imagen = itemView.findViewById(R.id.ex_img)
        text = itemView.findViewById(R.id.ex_name)
        carta = itemView.findViewById(R.id.carta)
    }

    //evento on click


    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v!!, adapterPosition)
    }
}

class RecyclerViewAdapter(var exerciselist: List<Exercise>,
                          var contexto: Context) : RecyclerView.Adapter<RecyclerViewHolder>() {
    var excerciselist: List<Exercise> = exerciselist
    var context: Context = contexto

    override fun onBindViewHolder(holder: RecyclerViewHolder?, position: Int) {
        holder!!.imagen!!.setImageResource(exerciselist[position].image_id)
        holder.text!!.text = exerciselist[position].name.toString()
        holder.setItemClickListener(ItemClickListener {
            view, p1 ->
            var intent = Intent(contexto,ViewExercise::class.java)
            intent.putExtra("image_id",exerciselist[position].image_id)
            intent.putExtra("name",exerciselist[position].name)
            contexto.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return exerciselist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_exercise, parent, false))
    }
}