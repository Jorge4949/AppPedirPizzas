package com.example.apppizzas.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PizzaModel(var nombre: String?, var ingredientes:MutableList<String>, var precio:Float = 10.0F):Parcelable