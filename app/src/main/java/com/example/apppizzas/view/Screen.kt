package com.example.apppizzas.view

import com.example.apppizzas.R

sealed class Screen(val ruta: String, val icono: Int) {
    object Lista : Screen("List", R.drawable.ic_home_button)
    object Cart : Screen("Cart", R.drawable.ic_cart_button)
    object Search : Screen("Search", R.drawable.ic_search_button)
}
