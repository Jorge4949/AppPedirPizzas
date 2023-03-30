package com.example.apppizzas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.R
import com.example.apppizzas.databinding.FragmentCartBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.CarroViewModel
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar


class CartFragment : Fragment(), funcionalidad_boton_quitar {
    lateinit var recyclerViewCarro: RecyclerView
    var adapter: ReciclerViewCarroAdapter ?= null
    val modelView:CarroViewModel by viewModels()
    lateinit var binding: FragmentCartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        val vista = binding.root

        recyclerViewCarro = binding.listadoCarro
        recyclerViewCarro.layoutManager = LinearLayoutManager(context)

        modelView.carro_liveData.observe(viewLifecycleOwner, Observer { pizza ->
            adapter?.let {
                updateView(pizza)
            }?:run {
                adapter = ReciclerViewCarroAdapter(pizza,this)
                recyclerViewCarro.adapter = adapter
            }
        })
        return vista
    }

    override fun onResume() {
        super.onResume()
        modelView.updateCart()
    }

    override fun quitar_del_carro(pizza: PizzaModel) {
        modelView.quitar_del_carro(pizza)
    }

    private fun updateView(updated_lista:MutableList<PizzaModel>){
        adapter?.updateCarro(updated_lista)
    }

}