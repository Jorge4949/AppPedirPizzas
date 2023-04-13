package com.example.apppizzas.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.apppizzas.R
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.ListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun recyclerViewPizzas(viewModel: ListViewModel = koinViewModel()) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Titulo()
        }
        items(viewModel.pizzas_disponibles) {
            pizzaLayout(it, viewModel)
        }
    }
}

@Composable
fun pizzaLayout(pizza: PizzaModel, viewModel: ListViewModel) {
    Column(modifier = Modifier
        .padding(vertical = 10.dp)
        .fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(id = R.drawable.pizza_image),
                contentDescription = "Imagen pizza"
            )
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
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
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = { viewModel.mostrarPersonalizar(pizza) },
                modifier = Modifier.padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            ) {
                Text(text = "Personalizar", color = Color.White)
            }
            Button(
                onClick = { viewModel.añadirPizza(pizza) },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(text = "Añadir al carro", color = Color.Green)
            }
        }
    }
}