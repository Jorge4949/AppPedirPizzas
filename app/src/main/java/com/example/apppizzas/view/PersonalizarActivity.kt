package com.example.apppizzas.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.viewModels
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
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        binding = ActivityPersonalizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("listado_pizzas", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val pizza:PizzaModel? = intent.getParcelableExtra("pizza")
        cargarVista(pizza)

        recyclerViewIngredientes = binding.personalizarPizza
        recyclerViewIngredientes.layoutManager = LinearLayoutManager(context)
        recyclerViewIngredientes.adapter = ReciclerViewPersonalizarAdapter(pizza!!.ingredientes, this)

        binding.botonAAdiralcarroPersonalizar.setOnClickListener {
            añadirPizzaPersonalizadAlCarro(pizza)
        }
    }

    override fun añadirPizzaPersonalizadAlCarro(pizza: PizzaModel) {
        viewModel.añadirPizzaPersonalizadAlCarro(pizza)
    }

    override fun cambiarEstadoIngrediente(checkbox: CheckBox) {
        viewModel.cambiarEstadoIngrediente(checkbox)
    }

    private fun cargarVista(pizza: PizzaModel?){
        binding.nombrePizzaPersonalizar.setText(pizza?.nombre)
        binding.precioPizzaPersonalizar.setText("${pizza?.precio} €")
    }
}