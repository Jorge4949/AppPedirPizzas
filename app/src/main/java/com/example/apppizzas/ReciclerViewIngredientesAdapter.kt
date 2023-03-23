package com.example.apppizzas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ReciclerViewIngredientesAdapter(private val lista_ingredientes: List<String>): RecyclerView.Adapter<ReciclerViewIngredientesAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewIngredientesAdapter.ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.pizza_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReciclerViewIngredientesAdapter.ViewHolder, position: Int) {

        val pizza: String = lista_ingredientes[position]

    }

    override fun getItemCount(): Int {
        return lista_ingredientes.size
    }

    public class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

}