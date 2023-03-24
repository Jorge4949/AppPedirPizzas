package com.example.apppizzas

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.PizzaLayoutBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.funcionalidad_botones_list

class ReciclerViewPizzasAdapter(private val lista_pizzas: List<PizzaModel>, private val listener: funcionalidad_botones_list):RecyclerView.Adapter<ReciclerViewPizzasAdapter.ViewHolder>() {

    lateinit var context:Context
    lateinit var binding: PizzaLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewPizzasAdapter.ViewHolder {
        context = parent.context
        binding = PizzaLayoutBinding.inflate(LayoutInflater.from(context))

        //val itemView = LayoutInflater.from(context).inflate(R.layout.pizza_layout, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ReciclerViewPizzasAdapter.ViewHolder, position: Int) {

        val pizza: PizzaModel = lista_pizzas[position]
        holder.bind(pizza)
    }

    override fun getItemCount(): Int {
        return lista_pizzas.size
    }

    public class ViewHolder(private val binding: PizzaLayoutBinding, private val listener: funcionalidad_botones_list):RecyclerView.ViewHolder(binding.root){


        /*Este codigo ya no es necesario ya que utilizo viewBinding
        var visibilidad_ingredientes = false
        val pizza_nombre = itemView.findViewById(R.id.nombre_pizza) as TextView
        val pizza_precio = itemView.findViewById(R.id.precio_pizza) as TextView
        val boton_añadir = itemView.findViewById(R.id.boton_añadiralcarro) as AppCompatButton
        val boton_personalizar = itemView.findViewById(R.id.boton_personalizar) as AppCompatButton
        val lista_ingredientes = itemView.findViewById(R.id.listado_ingredientes) as RecyclerView*/
        //val prueba_visibilidad = itemView.findViewById(R.id.prueba_visibilidad) as TextView

        fun bind(pizza:PizzaModel){
            binding.nombrePizza.setText(pizza.nombre)
            binding.precioPizza.text = "${pizza.precio.toString()} €"

            binding.botonAAdiralcarro.setOnClickListener{ listener.añadirPizzaAlCarro(pizza) }
            binding.botonPersonalizar.setOnClickListener{ listener.mostrarPersonalizar(pizza) }


        }

    }

}