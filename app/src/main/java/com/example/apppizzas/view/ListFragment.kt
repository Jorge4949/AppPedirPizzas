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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.apppizzas.R
import com.example.apppizzas.databinding.FragmentListBinding
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.ListViewModel
import com.example.apppizzas.viewModel.funcionalidad_botones_list


class ListFragment : Fragment(), funcionalidad_botones_list {
    val viewModel: ListViewModel by viewModels()

    lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val vista: View = binding.root

        binding.listadoPizzasCompose.setContent {
            recyclerViewPizzas(pizzas = viewModel.pizzas_disponibles)
        }

        return vista
    }

    override fun añadirPizzaAlCarro(pizza: PizzaModel) {
        viewModel.añadirPizza(pizza)
    }

    override fun mostrarPersonalizar(pizza: PizzaModel) {
        viewModel.mostrarPersonalizar(pizza)
    }

    @Composable
    fun recyclerViewPizzas(pizzas: ArrayList<PizzaModel>) {
        LazyColumn() {
            items(pizzas) {
                pizzaLayout(it)
            }
        }
    }

    @Composable
    fun pizzaLayout(pizza: PizzaModel) {
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Row(modifier = Modifier.align(CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.pizza_image),
                    contentDescription = "Imagen pizza"
                )
            }
            Row(modifier = Modifier.align(CenterHorizontally)) {
                Text(
                    text = "${pizza.nombre}",
                    style = TextStyle(
                        fontSize = TextUnit(30F, TextUnitType.Sp)
                    ),
                    modifier = Modifier.padding(horizontal = 30.dp)
                )
                Text(
                    text = "${pizza.precio} €",
                    style = TextStyle(
                        fontSize = TextUnit(30F, TextUnitType.Sp)
                    ),
                    modifier = Modifier
                )
            }
            Row(modifier = Modifier.align(CenterHorizontally)) {
                Button(
                    onClick = { mostrarPersonalizar(pizza) },
                    modifier = Modifier.padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                ) {
                    Text(text = "Personalizar", color = Color.White)
                }
                Button(
                    onClick = { añadirPizzaAlCarro(pizza) },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(text = "Añadir al carro", color = Color.Green)
                }
            }
        }
    }

}