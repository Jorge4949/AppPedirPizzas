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
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        context = this

        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        //val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        binding.navigation.setOnItemSelectedListener{
            when(it.toString().toLowerCase()) {
                "home" ->{
                    loadFragment(ListFragment())
                    //Toast.makeText(context,"BOTON HOME PULSADO", Toast.LENGTH_SHORT).show()
                    true
                }
                "cart"->{
                    loadFragment(CartFragment())
                    //Toast.makeText(context,"BOTON CART PULSADO", Toast.LENGTH_SHORT).show()
                    true
                }
                "search"->{
                    loadFragment(SearchFragment())
                    //Toast.makeText(context,"BOTON SEARCH PULSADO", Toast.LENGTH_SHORT).show()
                    true
                }else ->{
                    //Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
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

}