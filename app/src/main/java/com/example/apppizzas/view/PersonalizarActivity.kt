package com.example.apppizzas.view

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.apppizzas.viewModel.PersonalizarViewModel
import org.koin.androidx.compose.koinViewModel

class PersonalizarActivity : AppCompatActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this
        val pizza: PizzaModel? = intent.getParcelableExtra("pizza")
        setContent {
            if (pizza != null) {
                personalizarPizza(pizza = pizza)
            }
        }
    }

    @Composable
    fun personalizarPizza(pizza: PizzaModel, viewModel: PersonalizarViewModel = koinViewModel()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 80.dp)
        ) {
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
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 10.dp)
            ) {
                LazyColumn() {
                    items(pizza.ingredientes.count()) {
                        personalizarIngrediente(ingrediente = pizza.ingredientes[it])
                    }
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Button(
                    onClick = { viewModel.añadirPizzaPersonalizadAlCarro(pizza) },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(text = "Añadir al carro", color = Color.Green)
                }
            }
        }
    }

    @Composable
    fun personalizarIngrediente(
        ingrediente: String,
        viewModel: PersonalizarViewModel = koinViewModel()
    ) {
        Column() {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = ingrediente,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
                val checked = remember { mutableStateOf(false) }
                Checkbox(checked = checked.value, onCheckedChange = {
                    checked.value = it
                    viewModel.cambiarEstadoIngrediente(checked.value, ingrediente)
                })
            }
        }
    }

}