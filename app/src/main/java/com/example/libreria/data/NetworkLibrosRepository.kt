//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.data

import com.example.libreria.internet.LibrosApiService
import com.example.libreria.model.Libro
import retrofit2.HttpException
import java.io.IOException

//class NetworkLibrosRepository(
//    private val librosApiService: LibrosApiService
//) : LibrosRepository {
//
//    override suspend fun getLibros(query: String): List<Libro> {
//        //esto lo tengo nada más para probar que si me aparecen libros
//
//
////        return try {
////            val respuesta = librosApiService.buscarLibros(query)
////
////            if (respuesta.items.isNullOrEmpty()) {
////                emptyList()
////            } else {
////                respuesta.items.map { item ->
////                    Libro(
////                        id = item.id ?: "0",
////                        titulo = item.volumeInfo?.title ?: "Sin título",
////                        imagenUrl = item.volumeInfo?.imageLinks?.thumbnail?.replace(
////                            "http:",
////                            "https:"
////                        ) ?: ""
////                    )
////                }
////            }
////        } catch (e: HttpException) {
////
////            if (e.code() == 429) {
////                listOf(
////                    Libro(
////                        "error",
////                        "API bloqueada temporalmente (429). Espera unos minutos.",
////                        ""
////                    )
////                )
////            } else {
////                listOf(Libro("error", "Error de servidor: ${e.code()}", ""))
////            }
////        } catch (e: IOException) {
////            listOf(Libro("error", "Error de conexión: Revisa tu internet", ""))
////        }
//        }
//    }
//}

class NetworkLibrosRepository : LibrosRepository {

    override suspend fun getLibros(query: String): List<Libro> {
        return listOf(
            Libro("1", "El Principito", "https://covers.openlibrary.org/b/id/8233519-L.jpg"),
            Libro("2", "Cien Años de Soledad", "https://covers.openlibrary.org/b/id/12567808-L.jpg"),
            Libro("3", "Don Quijote de la Mancha", "https://covers.openlibrary.org/b/id/12567845-L.jpg"),
            Libro("4", "1984", "https://covers.openlibrary.org/b/id/10547081-L.jpg"),
            Libro("5", "El Hobbit", "https://covers.openlibrary.org/b/id/12567812-L.jpg"),
            Libro("6", "Crónica de una muerte anunciada", "https://covers.openlibrary.org/b/id/12567820-L.jpg"),
            Libro("7", "La Metamorfosis", "https://covers.openlibrary.org/b/id/12567830-L.jpg"),
            Libro("8", "Orgullo y Prejuicio", "https://covers.openlibrary.org/b/id/12567840-L.jpg"),
            Libro("9", "Fahrenheit 451", "https://covers.openlibrary.org/b/id/12567850-L.jpg"),
            Libro("10", "Harry Potter y la Piedra Filosofal", "https://covers.openlibrary.org/b/id/12567860-L.jpg")
        )
    }
}

