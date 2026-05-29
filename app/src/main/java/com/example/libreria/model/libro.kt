//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

// 1. La respuesta principal que devuelve el servidor de Google
@Serializable
data class RespuestaBusqueda(
    @SerialName("items") val items: List<ItemLibro>? = null
)
// 2. Cada libro individual de la lista
@Serializable
data class ItemLibro(
    @SerialName("id") val id: String? = null,
    @SerialName("volumeInfo") val volumeInfo: VolumeInfo? = null
)

// 3. Los detalles esenciales (Título e imágenes)
@Serializable
data class VolumeInfo(
    @SerialName("title") val title: String? = null,
    @SerialName("imageLinks") val imageLinks: ImageLinks? = null
)

// 4. Las URLs de las portadas
@Serializable
data class ImageLinks(
    @SerialName("thumbnail") val thumbnail: String? = null
)

// 5. El objeto nativo que tu HomeScreen usa para pintar la cuadrícula
data class Libro(
    val id: String,
    val titulo: String,
    val imagenUrl: String
)

// Esta clase se queda por si la requiere tu interfaz de servicio y evitar errores de compilación
@Serializable
data class DetalleLibro(
    val id: String? = null,
    val volumeInfo: VolumeInfo? = null
)