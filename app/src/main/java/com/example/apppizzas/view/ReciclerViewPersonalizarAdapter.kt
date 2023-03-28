package com.example.apppizzas.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.IngredientesLayoutBinding

class ReciclerViewPersonalizarAdapter(private val lista_ingredientes:List<String>):RecyclerView.Adapter<ReciclerViewPersonalizarAdapter.ViewHolder>() {

    lateinit var context: Context
    lateinit var binding: IngredientesLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewPersonalizarAdapter.ViewHolder {
        context = parent.context
        binding = IngredientesLayoutBinding.inflate(LayoutInflater.from(context))

        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReciclerViewPersonalizarAdapter.ViewHolder, position: Int) {
        val ingrediente:String = lista_ingredientes[position]
        holder.bind(ingrediente)
    }

    override fun getItemCount(): Int {
        return lista_ingredientes.size
    }

    public class ViewHolder(private val binding: IngredientesLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ingrediente:String){
            binding.Ingredientes.setText(ingrediente)
        }
    }
}