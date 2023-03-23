package com.example.apppizzas.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apppizzas.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    lateinit var binding: ActivitySearchBinding
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        binding.apply {
            homeButton.setOnClickListener {
                startActivity(Intent(context, MainActivity::class.java))
            }
            cartButton.setOnClickListener {
                startActivity(Intent(context, CartActivity::class.java))
            }
        }
    }
}