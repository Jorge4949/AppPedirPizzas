package com.example.apppizzas.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.apppizzas.model.PizzaModel


class ListViewModel(application: Application) : AndroidViewModel(application) {
    var sharedPreferences: SharedPreferences = application.getSharedPreferences("listado_pizzas", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()


    var pizza_margarita: PizzaModel = PizzaModel("Margarita", mutableListOf("Tomate","Queso"), 12F)
    var pizza_cuatroquesos: PizzaModel = PizzaModel("4 Quesos", mutableListOf("Tomate","Queso1","Queso2","Queso3","Queso4"))
    var pizza_carnivora: PizzaModel = PizzaModel("Carnivora", mutableListOf("Tomate","Queso","Pepperoni","Carne picada"))
    var pizzas_disponibles = arrayListOf<PizzaModel>(pizza_margarita,pizza_carnivora,pizza_cuatroquesos)

    public fun añadirPizza(pizza: PizzaModel){
        var num_pizzas_totales = sharedPreferences.getInt("num_pizzas_totales",0)
        var ingredientes_set:Set<String> = setOf()
        pizza.ingredientes.forEach {
            ingredientes_set.plus(it)
        }
        num_pizzas_totales ++
        editor.apply {
            putString("nombre_${num_pizzas_totales}",pizza.nombre)
            putFloat("precio_${num_pizzas_totales}",pizza.precio)
            putStringSet("ingredientes_${num_pizzas_totales}",ingredientes_set)
            putInt("pizza_num${num_pizzas_totales}",num_pizzas_totales)
            putInt("num_pizzas_totales", num_pizzas_totales)
        }.apply()
    }

}

interface funcionalidad_botones_list{
    fun añadirPizzaAlCarro(pizza: PizzaModel)
    fun mostrarPersonalizar(pizza: PizzaModel)
}