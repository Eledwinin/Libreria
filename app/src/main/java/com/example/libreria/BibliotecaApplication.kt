//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria

import android.app.Application
import com.example.libreria.data.AppContainer
import com.example.libreria.data.DefaultAppContainer

class BibliotecaApplication : Application() {
    // La caja de herramientas global
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}