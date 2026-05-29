//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.libreria.model.Libro

@Composable
fun HomeScreen(
    bibliotecaUiState: BibliotecaUiState,
    contentPadding: PaddingValues,
    onBuscar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "MI BIBLIOTECA",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(27.dp))

        // El Buscador
        var query by remember { mutableStateOf("") }
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar libros...") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onBuscar(query) }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 0.dp),
            trailingIcon = {
                IconButton(onClick = { onBuscar(query) }) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (bibliotecaUiState) {
            is BibliotecaUiState.Loading -> PantallaCarga(modifier = Modifier.fillMaxSize())
            is BibliotecaUiState.Success -> CuadriculaLibros(
                libros = bibliotecaUiState.libros,
                contentPadding = contentPadding
            )
            is BibliotecaUiState.Error -> PantallaError(mensaje = bibliotecaUiState.mensaje)
        }
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
fun PantallaError(mensaje: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "🚨 ERROR AL CONECTAR:", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error)
        Text(text = mensaje, color = MaterialTheme.colorScheme.error)
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
        contentPadding = PaddingValues(top = 6.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
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

            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(libro.imagenUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = libro.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.7f)
            )

            Text(
                text = libro.titulo.ifEmpty { "Libro sin título disponible" },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}