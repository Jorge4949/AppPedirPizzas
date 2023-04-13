package com.example.apppizzas.view

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apppizzas.model.Screen
import com.example.apppizzas.viewModel.CarroViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    lateinit var context: Context

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
                .fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Lista.ruta
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
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
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
        recyclerViewPizzas()
    }

    @Composable
    fun Carro() {
        recyclerViewCarro()
    }

    @Composable
    fun Search() {

    }

}