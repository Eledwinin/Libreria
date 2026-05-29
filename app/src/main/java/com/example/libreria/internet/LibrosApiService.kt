//creado por Edwin Mauricio Morales Rodriguez

package com.example.libreria.internet

import com.example.libreria.model.DetalleLibro
import com.example.libreria.model.RespuestaBusqueda
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LibrosApiService {
    @GET("volumes") // <-- Revisa que diga "volumes" en minúsculas y sin barras diagonales
    suspend fun buscarLibros(
        @Query("q") query: String
    ): RespuestaBusqueda
}