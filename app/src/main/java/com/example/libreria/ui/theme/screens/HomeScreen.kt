//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.libreria.model.Libro

@Composable
fun HomeScreen(
    bibliotecaUiState: BibliotecaUiState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
    ){
    when(bibliotecaUiState){
        is BibliotecaUiState.Loading -> PantallaCarga(modifier = modifier.fillMaxSize())
        is BibliotecaUiState.Success -> CuadriculaLibros(
            libros = bibliotecaUiState.libros,
            contentPadding = contentPadding,
            modifier = modifier.fillMaxSize()
        )
        is BibliotecaUiState.Error -> PantallaError(modifier = modifier.fillMaxSize())
    }

}

@Composable
fun PantallaCarga(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Buscando libros en Google Books...", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error al conectar con el servidor", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun CuadriculaLibros(
    libros: List<Libro>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = libros, key = { libro -> libro.id }) { libro ->
            TarjetaLibro(libro = libro)
        }
    }
}

@Composable
fun TarjetaLibro(libro: Libro, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Evaluamos si el URL está vacío para poner una imagen de respaldo
            val modeloImagen = if (libro.imagenUrl.isEmpty()) {
                "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=500" // Una foto de un libro real de internet
            } else {
                libro.imagenUrl
            }

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(modeloImagen)
                    .crossfade(true)
                    .build(),
                contentDescription = libro.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
            )

            Text(
                text = libro.titulo.ifEmpty { "Libro sin título disponible" }, // Respaldo por si viene vacío
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}