package com.example.apppizzas.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.AndroidViewModel
import com.example.apppizzas.databinding.ActivityPersonalizarBinding
import com.example.apppizzas.model.PizzaModel

class PersonalizarViewModel(application: Application) : AndroidViewModel(application) {
    var sharedPreferences: SharedPreferences = application.getSharedPreferences("listado_pizzas", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()
    var ingredientes_personalizados = sharedPreferences.getStringSet("ingredientes", mutableSetOf())

    public fun añadirPizzaPersonalizadAlCarro(pizza: PizzaModel) {
        var num_pizzas_totales = sharedPreferences.getInt("num_pizzas_totales",0)
        editor.putStringSet("ingredientes",ingredientes_personalizados)

        num_pizzas_totales ++
        editor.apply {
            putString("nombre_${num_pizzas_totales}",pizza.nombre)
            putFloat("precio_${num_pizzas_totales}",pizza.precio)
            putStringSet("ingredientes_${num_pizzas_totales}",sharedPreferences.getStringSet("ingredientes", mutableSetOf()))
            putInt("pizza_num${num_pizzas_totales}",num_pizzas_totales)
            putInt("num_pizzas_totales", num_pizzas_totales)
        }.apply()
    }

    public fun cambiarEstadoIngrediente(checkbox: CheckBox){
        if (checkbox.isChecked){
            ingredientes_personalizados!!.add(checkbox.text.toString())
        }else{
            ingredientes_personalizados!!.remove(checkbox.text.toString())
        }
    }

}
interface funcionalidad_personalizada{
    fun añadirPizzaPersonalizadAlCarro(pizza:PizzaModel)
    fun cambiarEstadoIngrediente(checkbox:CheckBox)
}