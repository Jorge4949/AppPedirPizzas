package com.example.apppizzas.view

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apppizzas.R
import com.example.apppizzas.model.PizzaModel
import com.example.apppizzas.viewModel.CarroViewModel
import com.example.apppizzas.viewModel.ListViewModel
import com.example.apppizzas.viewModel.funcionalidad_boton_quitar
import com.example.apppizzas.viewModel.funcionalidad_botones_list

class MainActivity : AppCompatActivity(), funcionalidad_botones_list, funcionalidad_boton_quitar {
    lateinit var context: Context
    val listViewModel: ListViewModel by viewModels()
    val cartViewModel: CarroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        setContent {
            LayoutCompleto()
        }

    }


    @Composable
    fun LayoutCompleto() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    Navigation(navController)
                }
            },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 20.dp)
                ) {
                    Titulo()
                }
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Lista.ruta,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Lista.ruta) { Lista() }
                        composable(Screen.Cart.ruta) { Carro() }
                        composable(Screen.Search.ruta) { Search() }
                    }
                }
            }
        }
    }

    @Composable
    fun Titulo() {
        Row() {
            Text(
                text = "PidePizza",
                style = TextStyle(
                    color = Color.Yellow,
                    background = Color.Green,
                    fontSize = TextUnit(value = 50F, type = TextUnitType.Sp),
                    textAlign = TextAlign.Center
                ),
            )
        }
    }

    @Composable
    fun Navigation(navController: NavController) {
        val items = listOf(
            Screen.Lista,
            Screen.Cart,
            Screen.Search
        )
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icono),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = screen.ruta) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.ruta } == true,
                    onClick = {
                        navController.navigate(screen.ruta) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }

    @Composable
    fun Lista() {
        recyclerViewPizzas(pizzas = listViewModel.pizzas_disponibles)
    }

    @Composable
    fun Carro() {
        cartViewModel.updateCart()
        val carro_liveData by cartViewModel.carro_liveData.observeAsState()
        carro_liveData?.let { recyclerViewCarro(pizzas = it) }
    }

    @Composable
    fun Search() {

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

    override fun añadirPizzaAlCarro(pizza: PizzaModel) {
        listViewModel.añadirPizza(pizza)
    }

    override fun mostrarPersonalizar(pizza: PizzaModel) {
        listViewModel.mostrarPersonalizar(pizza)
    }

    override fun quitar_del_carro(pizza: PizzaModel) {
        cartViewModel.quitar_del_carro(pizza)
    }
}