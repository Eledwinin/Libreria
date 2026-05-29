//creado por Edwin Mauricio Morales Rodriguez

package com.example.libreria.internet


import com.example.libreria.model.RespuestaBusqueda
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LibrosApiService {
    @GET("volumes")
    suspend fun buscarLibros(@Query("q") query: String): RespuestaBusqueda
}