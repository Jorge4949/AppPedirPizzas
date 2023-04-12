package com.example.apppizzas.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppizzas.model.PizzaModel
import kotlinx.coroutines.launch

class PersonalizarViewModel(application: Application) : AndroidViewModel(application) {
    var sharedPreferences: SharedPreferences =
        application.getSharedPreferences("listado_pizzas", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    lateinit var ingredientes_personalizados: MutableSet<String>

    init {
        inicializarDatos()
    }

    public fun añadirPizzaPersonalizadAlCarro(pizza: PizzaModel) {
        viewModelScope.launch {
            var num_pizzas_totales = sharedPreferences.getInt("num_pizzas_totales", 0)
            editor.putStringSet("ingredientes", ingredientes_personalizados)

            num_pizzas_totales++
            editor.apply {
                putString("nombre_${num_pizzas_totales}", pizza.nombre)
                putFloat("precio_${num_pizzas_totales}", pizza.precio)
                putStringSet(
                    "ingredientes_${num_pizzas_totales}",
                    sharedPreferences.getStringSet("ingredientes", mutableSetOf())
                )
                putInt("pizza_num${num_pizzas_totales}", num_pizzas_totales)
                putInt("num_pizzas_totales", num_pizzas_totales)
            }.apply()
        }
    }

    private fun inicializarDatos() {
        viewModelScope.launch {
            ingredientes_personalizados = mutableSetOf()
        }
    }

    public fun cambiarEstadoIngrediente(checked: Boolean, ingrediente: String) {
        viewModelScope.launch {
            if (checked) {
                ingredientes_personalizados?.add(ingrediente)
            } else {
                ingredientes_personalizados?.remove(ingrediente)
            }
        }
    }

}

interface funcionalidad_personalizada {
    fun añadirPizzaPersonalizadAlCarro(pizzaModel: PizzaModel)
    fun cambiarEstadoIngrediente(checked: Boolean, ingrediente: String)
}