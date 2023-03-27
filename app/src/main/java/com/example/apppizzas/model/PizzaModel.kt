package com.example.apppizzas.model

import android.os.Parcel
import android.os.Parcelable

data class PizzaModel(var nombre: String?, var ingredientes:MutableList<String>, var precio:Float = 10.0F):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        mutableListOf(parcel.createStringArrayList().toString()),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeFloat(precio)
        parcel.writeList(ingredientes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PizzaModel> {
        override fun createFromParcel(parcel: Parcel): PizzaModel {
            return PizzaModel(parcel)
        }

        override fun newArray(size: Int): Array<PizzaModel?> {
            return arrayOfNulls(size)
        }
    }

}