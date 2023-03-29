package com.example.apppizzas.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apppizzas.model.PizzaModel

class CarroViewModel(application: Application) : AndroidViewModel(application) {
    var sharedPreferences: SharedPreferences = application.getSharedPreferences("listado_pizzas", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    lateinit var carro:MutableList<PizzaModel>
    val carro_liveData: MutableLiveData<MutableList<PizzaModel>> by lazy{
        MutableLiveData<MutableList<PizzaModel>>()
    }
    public fun updateCart(){
        carro = mutableListOf()

        for (i in 1..sharedPreferences.getInt("num_pizzas_totales",0)){
            val nombre_pizza = sharedPreferences.getString("nombre_${i}","Vacio")
            val precio_pizza = sharedPreferences.getFloat("precio_${i}", 0f)
            val ingredientes_set_pizza = sharedPreferences.getStringSet("ingredientes_${i}", mutableSetOf())
            val ingredientes_pizza:MutableList<String> = ingredientes_set_pizza!!.toMutableList()
            val pizza = PizzaModel(nombre_pizza!!,ingredientes_pizza,precio_pizza)
            if (nombre_pizza != "Vacio") {
                carro += pizza
            }
        }
        carro_liveData.setValue(carro)
    }
    public fun quitar_del_carro(pizza: PizzaModel){
        var num_pizza: Int = 0
        for (i in 1..sharedPreferences.getInt("num_pizzas_totales", 0)) {
            if (pizza.nombre == sharedPreferences.getString("nombre_${i}", "") &&
                pizza.precio.equals(sharedPreferences.getFloat("precio_${i}", 0f)) &&
                pizza.ingredientes.containsAll(sharedPreferences.getStringSet("ingredientes_${i}", mutableSetOf())!!)
            ){
                num_pizza = i
                break
            }
        }

        if (num_pizza != 0) {
            editor.apply {
                remove("nombre_${num_pizza}")
                remove("precio_${num_pizza}")
                remove("ingredientes_${num_pizza}")
            }.apply()
            updateCart()
        }
    }
}

interface funcionalidad_boton_quitar{
    fun quitar_del_carro(pizza: PizzaModel)
}