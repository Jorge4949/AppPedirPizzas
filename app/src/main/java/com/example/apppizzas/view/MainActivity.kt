package com.example.apppizzas.view

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.apppizzas.R
import com.example.apppizzas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var context:Context
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        sharedPreferences = getSharedPreferences("listado_pizzas", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        binding.navigation.setOnItemSelectedListener{
            when(it.toString().toLowerCase()) {
                "home" ->{
                    loadFragment(ListFragment())
                    true
                }
                "cart"->{
                    loadFragment(CartFragment())
                    true
                }
                "search"->{
                    loadFragment(SearchFragment())
                    true
                }else ->{
                    true
                }
            }
        }

    }
    override fun onStart() {
        super.onStart()
        binding.navigation.selectedItemId = binding.navigation.menu[0].itemId
    }
    private fun loadFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.zona_fragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        editor.clear().apply()
    }

    override fun onStop() {
        super.onStop()
        editor.clear().apply()
    }

}