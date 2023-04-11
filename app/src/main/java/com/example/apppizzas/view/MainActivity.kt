package com.example.apppizzas.view

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.apppizzas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var context: Context
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        sharedPreferences = getSharedPreferences("listado_pizzas", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.tituloCompose.setContent {
            titulo()
        }

        binding.navigation.setOnItemSelectedListener {
            when (it.toString().toLowerCase()) {
                "home" -> {
                    loadFragment(ListFragment())
                    true
                }
                "cart" -> {
                    loadFragment(CartFragment())
                    true
                }
                "search" -> {
                    loadFragment(SearchFragment())
                    true
                }
                else -> {
                    true
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        binding.navigation.selectedItemId = binding.navigation.menu[0].itemId
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.zonaFragment.id, fragment)
        fragmentTransaction.commit()
    }

    @Composable
    fun titulo() {
        Text(
            text = "PidePizza",
            style = TextStyle(
                color = Color.Yellow,
                background = Color.Green,
                fontSize = TextUnit(value = 50F, type = TextUnitType.Sp),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(50.dp, 40.dp)
        )
    }

}