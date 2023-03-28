package com.example.apppizzas.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.lifecycle.AndroidViewModel
import com.example.apppizzas.databinding.ActivityPersonalizarBinding
import com.example.apppizzas.model.PizzaModel

class PersonalizarViewModel(application: Application) : AndroidViewModel(application) {
    var sharedPreferences: SharedPreferences = application.getSharedPreferences("listado_pizzas", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()


    public fun añadirPizzaPersonalizadAlCarro(pizza: PizzaModel) {

    }

}
interface funcionalidad_personalizada{
    fun añadirPizzaPersonalizadAlCarro(pizza:PizzaModel)
}