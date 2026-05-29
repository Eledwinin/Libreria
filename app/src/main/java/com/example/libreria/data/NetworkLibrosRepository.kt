//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.data

import com.example.libreria.internet.LibrosApiService
import com.example.libreria.model.Libro
import retrofit2.HttpException
import java.io.IOException

class NetworkLibrosRepository(
    private val librosApiService: LibrosApiService
) : LibrosRepository {

    override suspend fun getLibros(query: String): List<Libro> {
        //esto lo tengo nada más para probar que si me aparecen libros

//        return listOf(
//            Libro(
//                "1",
//                "Libro de Prueba 1",
//                "https://images.unsplash.com/photo-1543002588-bfa74002ed7e"
//            ),
//            Libro(
//                "2",
//                "Libro de Prueba 2",
//                "https://images.unsplash.com/photo-1512820790803-83ca734da794"
//            )
//        )
        return try {
            val respuesta = librosApiService.buscarLibros(query)

            if (respuesta.items.isNullOrEmpty()) {
                emptyList()
            } else {
                respuesta.items.map { item ->
                    Libro(
                        id = item.id ?: "0",
                        titulo = item.volumeInfo?.title ?: "Sin título",
                        imagenUrl = item.volumeInfo?.imageLinks?.thumbnail?.replace(
                            "http:",
                            "https:"
                        ) ?: ""
                    )
                }
            }
        } catch (e: HttpException) {

            if (e.code() == 429) {
                listOf(
                    Libro(
                        "error",
                        "API bloqueada temporalmente (429). Espera unos minutos.",
                        ""
                    )
                )
            } else {
                listOf(Libro("error", "Error de servidor: ${e.code()}", ""))
            }
        } catch (e: IOException) {
            listOf(Libro("error", "Error de conexión: Revisa tu internet", ""))
        }
    }
}