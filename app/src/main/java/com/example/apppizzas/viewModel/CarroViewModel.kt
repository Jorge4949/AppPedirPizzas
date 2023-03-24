package com.example.apppizzas.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.apppizzas.model.PizzaModel

class CarroViewModel:ViewModel() {
    //public val carro:MutableList<PizzaModel> = ListViewMod

    var pizza_margarita: PizzaModel = PizzaModel("Margarita", mutableListOf("Tomate","Queso"), 12F)
    var pizza_cuatroquesos: PizzaModel = PizzaModel("4 Quesos", mutableListOf("Tomate","Queso1","Queso2","Queso3","Queso4"))
    var pizza_carnivora: PizzaModel = PizzaModel("Carnivora", mutableListOf("Tomate","Queso","Pepperoni","Carne picada"))

    //coger el array del sharedpreferences
    var pizzas_enelcarro = arrayListOf<PizzaModel>(pizza_margarita,pizza_carnivora,pizza_cuatroquesos)

    //igual que en el listviewmodel
    public fun quitar_del_carro(pizza: PizzaModel){
        if (pizzas_enelcarro.contains(pizza)) {
            pizzas_enelcarro.removeAt(pizzas_enelcarro.indexOf(pizza))
        }else{
            println("${pizza.nombre} ya esta borrada aunque no la veas")
        }
    }
}

interface funcionalidad_boton_quitar{
    fun quitar_del_carro(pizza: PizzaModel)
}