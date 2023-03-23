package com.example.apppizzas.viewModel

import androidx.lifecycle.ViewModel
import com.example.apppizzas.model.PizzaModel

class ListViewModel:ViewModel() {
    var pizza_margarita: PizzaModel = PizzaModel("Margarita", mutableListOf("Tomate","Queso"), 12F)
    var pizza_cuatroquesos: PizzaModel = PizzaModel("4 Quesos", mutableListOf("Tomate","Queso1","Queso2","Queso3","Queso4"))
    var pizza_carnivora: PizzaModel = PizzaModel("Carnivora", mutableListOf("Tomate","Queso","Pepperoni","Carne picada"))
    var pizzas_disponibles = arrayListOf<PizzaModel>(pizza_margarita,pizza_carnivora,pizza_cuatroquesos)

    public val carro:MutableList<PizzaModel> = mutableListOf()

    public fun añadirPizza(pizza: PizzaModel){
        carro.add(pizza)
    }
}