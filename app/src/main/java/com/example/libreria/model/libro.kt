package com.example.libreria.model

import kotlinx.serialization.Serializable

@Serializable
data class RespuestaBusqueda(
    val items: List<ItemLibro>? = null
)

@Serializable
data class ItemLibro(
    val id: String? = null,
    val volumeInfo: VolumeInfo? = null
)

@Serializable
data class VolumeInfo(
    val title: String? = null,
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    val thumbnail: String? = null
)


data class Libro(
    val id: String,
    val titulo: String,
    val imagenUrl: String
)