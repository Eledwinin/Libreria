//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.data

import com.example.libreria.model.Libro
import com.example.libreria.model.RespuestaBusqueda

interface LibrosRepository {

    suspend fun getLibros(query: String): List<Libro>
}