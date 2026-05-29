package com.example.libreria

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.libreria.ui.theme.LibreriaTheme
import com.example.libreria.ui.theme.screens.BibliotecaViewModel
import com.example.libreria.ui.theme.screens.HomeScreen
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppBiblioteca()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBiblioteca() {

    val viewModel: BibliotecaViewModel = viewModel(factory = BibliotecaViewModel.Factory)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mi Biblioteca Virtual") })
        }
    ) { innerPadding ->
        HomeScreen(
            bibliotecaUiState = viewModel.bibliotecaUiState,
            contentPadding = innerPadding
        )
    }
}
