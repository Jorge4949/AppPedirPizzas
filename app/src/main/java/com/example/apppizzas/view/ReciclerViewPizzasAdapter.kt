package com.example.apppizzas.view

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.PizzaLayoutBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.funcionalidad_botones_list

class ReciclerViewPizzasAdapter(private val lista_pizzas: List<PizzaModel>, private val listener: funcionalidad_botones_list):RecyclerView.Adapter<ReciclerViewPizzasAdapter.ViewHolder>() {

    lateinit var context:Context
    lateinit var binding: PizzaLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = PizzaLayoutBinding.inflate(LayoutInflater.from(context))

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pizza: PizzaModel = lista_pizzas[position]
        holder.bind(pizza)
    }

    override fun getItemCount(): Int {
        return lista_pizzas.size
    }

    public class ViewHolder(private val binding: PizzaLayoutBinding, private val listener: funcionalidad_botones_list):RecyclerView.ViewHolder(binding.root){
        fun bind(pizza:PizzaModel){
            binding.nombrePizza.text = pizza.nombre
            binding.precioPizza.text = "${pizza.precio.toString()} €"

            binding.botonAAdiralcarro.setOnClickListener{ listener.añadirPizzaAlCarro(pizza) }
            binding.botonPersonalizar.setOnClickListener{ listener.mostrarPersonalizar(pizza) }

        }

    }

}