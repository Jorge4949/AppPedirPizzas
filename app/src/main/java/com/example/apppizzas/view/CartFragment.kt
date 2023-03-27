package com.example.apppizzas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppizzas.R
import com.example.apppizzas.ReciclerViewCarroAdapter
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.CarroViewModel
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar


class CartFragment : Fragment(), funcionalidad_boton_quitar {
    lateinit var recyclerViewCarro: RecyclerView
    val modelView:CarroViewModel by viewModels()
    /*lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*sharedPreferences = requireActivity().getSharedPreferences("listado_pizzas", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val vista = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerViewCarro = vista.findViewById(R.id.listado_carro)
        recyclerViewCarro.layoutManager = LinearLayoutManager(context)
        updateView()

        return vista
    }

    override fun onResume() {
        super.onResume()
    }

    override fun quitar_del_carro(pizza: PizzaModel) {
        modelView.quitar_del_carro(pizza)
        updateView()
    }

    private fun updateView(){
        modelView.updateCart()
        recyclerViewCarro.adapter = ReciclerViewCarroAdapter(modelView.carro,this)
    }

}