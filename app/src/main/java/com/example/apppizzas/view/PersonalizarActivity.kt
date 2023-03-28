package com.example.apppizzas.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.databinding.ActivityPersonalizarBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.PersonalizarViewModel
import com.example.apppizzas.viewModel.funcionalidad_personalizada

class PersonalizarActivity : AppCompatActivity(), funcionalidad_personalizada {
    lateinit var binding: ActivityPersonalizarBinding
    lateinit var recyclerViewIngredientes: RecyclerView
    lateinit var context:Context
    val viewModel:PersonalizarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        binding = ActivityPersonalizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pizza:PizzaModel? = intent.getParcelableExtra("pizza")
        cargarVista(pizza!!)

        recyclerViewIngredientes = binding.personalizarPizza
        recyclerViewIngredientes.layoutManager = LinearLayoutManager(context)
        recyclerViewIngredientes.adapter = ReciclerViewPersonalizarAdapter(pizza!!.ingredientes)

        binding.botonAAdiralcarroPersonalizar.setOnClickListener {
            var ingredientes:MutableList<String> = mutableListOf()
            binding.personalizarPizza.children.forEach {
                if (it.isChecked)
            }
            añadirPizzaPersonalizadAlCarro(pizza_personalizada)
        }
    }

    override fun añadirPizzaPersonalizadAlCarro(pizza: PizzaModel) {
        viewModel.añadirPizzaPersonalizadAlCarro(pizza)
    }

    private fun cargarVista(pizza: PizzaModel){
        binding.nombrePizzaPersonalizar.setText(pizza.nombre)
        binding.precioPizzaPersonalizar.setText("${pizza.precio} €")
    }
}