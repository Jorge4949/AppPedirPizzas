package com.example.apppizzas.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.ReciclerViewCarroAdapter
import com.example.apppizzas.databinding.ActivityCartBinding
import com.example.apppizzas.viewModel.CarroViewModel
import com.example.apppizzas.viewModel.MainViewModel

class CartActivity : AppCompatActivity() {

    lateinit var binding: ActivityCartBinding
    lateinit var context: Context
    lateinit var recyclerViewCarro: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        val viewModel: CarroViewModel by viewModels()

        recyclerViewCarro = binding.elementosCarro
        recyclerViewCarro.layoutManager = LinearLayoutManager(context)

        //recyclerViewCarro.adapter = ReciclerViewCarroAdapter(carro)  Añadir las pizzas que estan en la variable carro de los viewModel

        binding.apply {
            homeButton.setOnClickListener {
                startActivity(Intent(context, MainActivity::class.java))
            }
            searchButton.setOnClickListener {
                startActivity(Intent(context, SearchActivity::class.java))
            }
        }
    }
}