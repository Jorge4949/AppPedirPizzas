package com.example.apppizzas.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.IngredientesLayoutBinding
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar
import com.example.apppizzas.viewModel.funcionalidad_personalizada

class ReciclerViewPersonalizarAdapter(
    private val lista_ingredientes:List<String>,
    private val listener: funcionalidad_personalizada)
    :RecyclerView.Adapter<ReciclerViewPersonalizarAdapter.ViewHolder>() {

    lateinit var context: Context
    lateinit var binding: IngredientesLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewPersonalizarAdapter.ViewHolder {
        context = parent.context
        binding = IngredientesLayoutBinding.inflate(LayoutInflater.from(context))

        return  ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ReciclerViewPersonalizarAdapter.ViewHolder, position: Int) {
        val ingrediente:String = lista_ingredientes[position]
        holder.bind(ingrediente)
    }

    override fun getItemCount(): Int {
        return lista_ingredientes.size
    }

    public class ViewHolder(private val binding: IngredientesLayoutBinding,private val listener: funcionalidad_personalizada):RecyclerView.ViewHolder(binding.root){
        fun bind(ingrediente:String){
            binding.Ingredientes.setText(ingrediente)
            binding.Ingredientes.setOnClickListener {
                listener.cambiarEstadoIngrediente(it as CheckBox)
            }
        }
    }
}