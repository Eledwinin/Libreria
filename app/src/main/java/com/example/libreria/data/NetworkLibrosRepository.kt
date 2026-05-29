//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.data

import com.example.libreria.internet.LibrosApiService
import com.example.libreria.model.Libro

class NetworkLibrosRepository(
    private val librosApiService: LibrosApiService
) : LibrosRepository {

    override suspend fun getLibros(query: String): List<Libro> {
        val listaArmada = mutableListOf<Libro>()

        try {
            // haremos una sola llamada para traer toda la informacion
            val respuesta = librosApiService.buscarLibros(query)

            // extramos los datos de cda libro
            respuesta.items?.forEach { item ->


                val tituloSeguro = item.volumeInfo?.title ?: "Sin título"
                val urlMiniatura = item.volumeInfo?.imageLinks?.thumbnail ?: ""


                val urlSegura = urlMiniatura.replace("http://", "https://")


                    val libroFormateado = Libro(
                        id = item.id ?: "Sin ID",
                        titulo = tituloSeguro,
                        imagenUrl = urlSegura
                    )
                    listaArmada.add(libroFormateado)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return listaArmada
    }
}