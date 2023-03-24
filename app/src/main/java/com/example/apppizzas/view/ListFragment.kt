package com.example.apppizzas.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.R
import com.example.apppizzas.ReciclerViewPizzasAdapter
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.ListViewModel
import com.example.apppizzas.viewModel.funcionalidad_botones_list


class ListFragment : Fragment(), funcionalidad_botones_list {
    val viewModel: ListViewModel by viewModels()
    lateinit var recyclerViewPizzas: RecyclerView

    var sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("listado_pizzas",MODE_PRIVATE)
    var editor: Editor = sharedPreferences.edit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val vista:View = inflater.inflate(R.layout.fragment_list, container, false)



        recyclerViewPizzas = vista.findViewById(R.id.listado_pizzas)
        recyclerViewPizzas.layoutManager = LinearLayoutManager(context)
        recyclerViewPizzas.adapter = ReciclerViewPizzasAdapter(viewModel.pizzas_disponibles,this)

        //Null pointer exception por esto de abajo
       /* val nombre_pizza:String = vista.findViewById<View?>(R.id.nombre_pizza).toString()
       val precio_pizza:Float = vista.findViewById<View?>(R.id.precio_pizza).toString().toFloat()
       val ingredientes_pizza:MutableList<String> =
        val ingredientes_pizza:MutableList<String> = mutableListOf("Tomate","Queso")
        val boton_añadir:AppCompatButton = vista.findViewById(R.id.boton_añadiralcarro) as AppCompatButton
        boton_añadir.setOnClickListener{
            //viewModel.añadirPizza(PizzaModel(nombre_pizza, ingredientes_pizza,precio_pizza))
            Toast.makeText(super.getContext(),"Pizza añadida al carro",Toast.LENGTH_SHORT).show()
            println(viewModel.carro.toString())
        }*/

        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun añadirPizzaAlCarro(pizza: PizzaModel) {
        viewModel.añadirPizza(pizza)
        //HACIENDO PRUEBAS CON SHAREDPREFERENCES PARA AÑADIR UN SET DE STRINGS CON LOS INGREDIENTES
        var ingredientes_set:Set<String> = s
        pizza.ingredientes.forEach {
            ingredientes_set.
        }
        editor.apply {
            putString("nombre",pizza.nombre)
            putFloat("Precio",pizza.precio)
            putStringSet()
        }
    }

    override fun mostrarPersonalizar(pizza: PizzaModel) {

    }


}