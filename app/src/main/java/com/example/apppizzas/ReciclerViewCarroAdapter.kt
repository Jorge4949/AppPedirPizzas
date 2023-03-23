package com.example.apppizzas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.model.PizzaModel

class ReciclerViewCarroAdapter(private val carro: List<PizzaModel>): RecyclerView.Adapter<ReciclerViewCarroAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewCarroAdapter.ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.pizzaenelcarro_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReciclerViewCarroAdapter.ViewHolder, position: Int) {

        val pizza: PizzaModel = carro[position]

    }

    override fun getItemCount(): Int {
        return carro.size
    }

    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

}