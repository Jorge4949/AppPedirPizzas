package com.example.apppizzas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.PizzaLayoutBinding
import com.example.apppizzas.model.PizzaModel

class ReciclerViewPizzasAdapter(private val lista_pizzas: List<PizzaModel>):RecyclerView.Adapter<ReciclerViewPizzasAdapter.ViewHolder>() {

    lateinit var context:Context
    lateinit var binding: PizzaLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReciclerViewPizzasAdapter.ViewHolder {
        context = parent.context
        //binding = PizzaLayoutBinding.inflate(LayoutInflater.from(context))
        val itemView = LayoutInflater.from(context).inflate(R.layout.pizza_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReciclerViewPizzasAdapter.ViewHolder, position: Int) {

        val pizza: PizzaModel = lista_pizzas[position]
        holder.bind(pizza, this.context)
    }

    override fun getItemCount(): Int {
        return lista_pizzas.size
    }

    public class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


        var visibilidad_ingredientes = false
        val pizza_nombre = itemView.findViewById(R.id.nombre_pizza_main) as TextView
        val pizza_precio = itemView.findViewById(R.id.precio_pizza) as TextView
        val boton_añadir = itemView.findViewById(R.id.boton_añadiralcarro) as AppCompatButton
        val boton_personalizar = itemView.findViewById(R.id.boton_personalizar) as AppCompatButton
        val lista_ingredientes = itemView.findViewById(R.id.listado_ingredientes) as RecyclerView
        //val prueba_visibilidad = itemView.findViewById(R.id.prueba_visibilidad) as TextView

        fun bind(pizza:PizzaModel, context: Context){
            pizza_nombre.setText(pizza.nombre)
            pizza_precio.text = "${pizza.precio.toString()} €"
            //boton_añadir.setOnClickListener{ MainViewModel.añadirPizza(pizza) }
            boton_personalizar.setOnClickListener {
                if (!visibilidad_ingredientes){
                    lista_ingredientes.visibility = View.VISIBLE
                    //prueba_visibilidad.visibility = View.VISIBLE
                    visibilidad_ingredientes = true
                }else{
                    lista_ingredientes.visibility = View.INVISIBLE
                    //prueba_visibilidad.visibility = View.INVISIBLE
                    visibilidad_ingredientes = false
                }
            }
        }

    }

}