package com.example.apppizzas.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.R
import com.example.apppizzas.ReciclerViewPizzasAdapter
import com.example.apppizzas.databinding.FragmentListBinding
import com.example.apppizzas.viewModel.ListViewModel
import com.example.apppizzas.viewModel.MainViewModel


class ListFragment : Fragment() {
    lateinit var recyclerViewPizzas: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val vista:View = inflater.inflate(R.layout.fragment_list, container, false)
        val viewModel: ListViewModel by viewModels()
        recyclerViewPizzas = vista.findViewById(R.id.listado_pizzas)
        recyclerViewPizzas.layoutManager = LinearLayoutManager(context)
        recyclerViewPizzas.adapter = ReciclerViewPizzasAdapter(viewModel.pizzas_disponibles)
        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}