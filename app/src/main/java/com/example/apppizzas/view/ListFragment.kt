package com.example.apppizzas.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RestrictTo.Scope
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.R
import com.example.apppizzas.databinding.FragmentListBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.ListViewModel
import com.example.apppizzas.viewModel.funcionalidad_botones_list


class ListFragment : Fragment(), funcionalidad_botones_list {
    val viewModel: ListViewModel by viewModels()
    lateinit var recyclerViewPizzas: RecyclerView

    lateinit var binding: FragmentListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentListBinding.inflate(inflater,container,false)
        val vista:View = binding.root

        recyclerViewPizzas = binding.listadoPizzas
        recyclerViewPizzas.layoutManager = LinearLayoutManager(context)
        recyclerViewPizzas.adapter = ReciclerViewPizzasAdapter(viewModel.pizzas_disponibles,this)

        return vista
    }

    override fun añadirPizzaAlCarro(pizza: PizzaModel) {
        viewModel.añadirPizza(pizza)
    }

    override fun mostrarPersonalizar(pizza: PizzaModel) {
        viewModel.mostrarPersonalizar(pizza)
    }
}