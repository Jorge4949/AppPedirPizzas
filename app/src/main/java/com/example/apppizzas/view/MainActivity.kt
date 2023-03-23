package com.example.apppizzas.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.R
import com.example.apppizzas.ReciclerViewPizzasAdapter
import com.example.apppizzas.databinding.ActivityMainBinding
import com.example.apppizzas.onFragmentActionListener
import com.example.apppizzas.viewModel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        context = this

        val viewModel: MainViewModel by viewModels()


        //val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        binding.navigation.setOnItemSelectedListener{
            when(it.toString().toLowerCase()) {
                "home" ->{
                    loadFragment(ListFragment())
                    //Toast.makeText(context,"BOTON HOME PULSADO", Toast.LENGTH_SHORT).show()
                    true
                }
                "cart"->{
                    loadFragment(CartFragment())
                    //Toast.makeText(context,"BOTON CART PULSADO", Toast.LENGTH_SHORT).show()
                    true
                }
                "search"->{
                    loadFragment(SearchFragment())
                    //Toast.makeText(context,"BOTON SEARCH PULSADO", Toast.LENGTH_SHORT).show()
                    true
                }else ->{
                    //Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
                    true
                }

            }
        }
        /*
        recyclerViewPizzas = binding.listadoPizzas
        recyclerViewPizzas.layoutManager = LinearLayoutManager(this)
        recyclerViewPizzas.adapter = ReciclerViewPizzasAdapter(viewModel.pizzas_disponibles)*/

        //asignar ingredientes_layout para agregar recycler view dentro de recycler view
        //recyclerViewIngredientes = binding.listadoPizzas.


        /*binding.apply {
            cartButton.setOnClickListener {
                startActivity(Intent(context, CartActivity::class.java))
            }
            searchButton.setOnClickListener {
                startActivity(Intent(context, SearchActivity::class.java))
            }

        }*/

    }
    private fun loadFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.zona_fragment, fragment)
        fragmentTransaction.commit()
    }

}