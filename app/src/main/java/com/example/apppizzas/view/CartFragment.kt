package com.example.apppizzas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.apppizzas.R
import com.example.apppizzas.databinding.FragmentCartBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.CarroViewModel
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar

class CartFragment : Fragment(), funcionalidad_boton_quitar {
    val modelView: CarroViewModel by viewModels()
    lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val vista = binding.root

        modelView.carro_liveData.observe(viewLifecycleOwner, Observer { pizza ->
            binding.listadoCarroCompose.setContent {
                recyclerViewCarro(pizzas = pizza)
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

    @Composable
    fun recyclerViewCarro(pizzas: MutableList<PizzaModel>) {
        LazyColumn() {
            items(pizzas) {
                pizzaEnElCarroLayout(it)
            }
        }
    }

    @Composable
    fun pizzaEnElCarroLayout(pizza: PizzaModel) {
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.pizza_image),
                    contentDescription = "Imagen pizza"
                )
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "${pizza.nombre}", style = TextStyle(
                        fontSize = TextUnit(30F, TextUnitType.Sp)
                    ), modifier = Modifier.padding(horizontal = 30.dp)
                )
                Text(
                    text = "${pizza.precio} €", style = TextStyle(
                        fontSize = TextUnit(30F, TextUnitType.Sp)
                    ), modifier = Modifier
                )
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = { quitar_del_carro(pizza) },
                    modifier = Modifier.padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                ) {
                    Text(text = "Quitar del carro", color = Color.White)
                }
            }
        }
    }

}