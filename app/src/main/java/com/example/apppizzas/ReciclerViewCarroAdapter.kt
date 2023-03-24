package com.example.apppizzas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.PizzaenelcarroLayoutBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar

class ReciclerViewCarroAdapter(private val carro: List<PizzaModel>,private val listener: funcionalidad_boton_quitar): RecyclerView.Adapter<ReciclerViewCarroAdapter.ViewHolder>() {

    lateinit var binding: PizzaenelcarroLayoutBinding
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewCarroAdapter.ViewHolder {
        context = parent.context
        binding = PizzaenelcarroLayoutBinding.inflate(LayoutInflater.from(context))

        //val itemView = LayoutInflater.from(context).inflate(R.layout.pizzaenelcarro_layout, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ReciclerViewCarroAdapter.ViewHolder, position: Int) {

        val pizza: PizzaModel = carro[position]
        holder.bind(pizza, this.context)
    }

    override fun getItemCount(): Int {
        return carro.size
    }

    public class ViewHolder(private val binding: PizzaenelcarroLayoutBinding, private val listener: funcionalidad_boton_quitar): RecyclerView.ViewHolder(binding.root){

        /*val pizza_nombre= itemView.findViewById(R.id.nombre_pizza_enelcarro) as TextView
        val pizza_precio = itemView.findViewById(R.id.precio_pizza_enelcarro) as TextView
        val boton_quitar = itemView.findViewById(R.id.boton_quitardelcarro) as AppCompatButton*/

        fun bind (pizza: PizzaModel, context: Context){
            binding.nombrePizzaEnelcarro.setText(pizza.nombre)
            binding.precioPizzaEnelcarro.text = "${pizza.precio.toString()} €"
            binding.botonQuitardelcarro.setOnClickListener {
                listener.quitar_del_carro(pizza)
            }
        }


    }

}