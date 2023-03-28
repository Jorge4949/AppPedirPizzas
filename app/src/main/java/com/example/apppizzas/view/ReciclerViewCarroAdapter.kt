package com.example.apppizzas.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.PizzaenelcarroLayoutBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar

class ReciclerViewCarroAdapter(carro: List<PizzaModel>,private val listener: funcionalidad_boton_quitar): RecyclerView.Adapter<ReciclerViewCarroAdapter.ViewHolder>() {

    lateinit var binding: PizzaenelcarroLayoutBinding
    lateinit var context: Context

    val lista_carro:ArrayList<PizzaModel> = ArrayList(carro)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        binding = PizzaenelcarroLayoutBinding.inflate(LayoutInflater.from(context))

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pizza: PizzaModel = lista_carro[position]
        holder.bind(pizza, this.context)
    }

    override fun getItemCount(): Int {
        return lista_carro.size
    }

    fun updateCarro(lista_pizza:MutableList<PizzaModel>){
        lista_carro.clear()
        lista_carro.addAll(lista_pizza)
        notifyDataSetChanged()
    }

    public class ViewHolder(private val binding: PizzaenelcarroLayoutBinding, private val listener: funcionalidad_boton_quitar): RecyclerView.ViewHolder(binding.root){

        fun bind (pizza: PizzaModel, context: Context){
            binding.nombrePizzaEnelcarro.setText(pizza.nombre)
            binding.precioPizzaEnelcarro.text = "${pizza.precio.toString()} €"
            binding.botonQuitardelcarro.setOnClickListener {
                listener.quitar_del_carro(pizza)
            }
        }


    }

}