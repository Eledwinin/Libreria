//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.data

import com.example.libreria.model.Libro

interface LibrosRepository {

    //esto dice que quien use esa funcion tiene que traer una lista de libros completa
    suspend fun getLibros (query: String): List<Libro>
}