package com.example.apppizzas.view

import android.app.Application
import com.example.apppizzas.viewModel.CarroViewModel
import com.example.apppizzas.viewModel.ListViewModel
import com.example.apppizzas.viewModel.PersonalizarViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::CarroViewModel)
    viewModelOf(::PersonalizarViewModel)
}

class KoinApp:Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(viewModelModule)
        }
    }
}
