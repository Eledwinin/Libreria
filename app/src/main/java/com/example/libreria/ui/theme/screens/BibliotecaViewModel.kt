//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.libreria.BibliotecaApplication
import com.example.libreria.data.LibrosRepository
import com.example.libreria.model.Libro
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BibliotecaUiState {
    data class Success(val libros: List<Libro>) : BibliotecaUiState
    object Error : BibliotecaUiState
    object Loading : BibliotecaUiState
}

class BibliotecaViewModel(private val librosRepository: LibrosRepository) : ViewModel() {

    var bibliotecaUiState: BibliotecaUiState by mutableStateOf(BibliotecaUiState.Loading)
        private set

    init {
        getLibrosDatos("android")
    }

    fun getLibrosDatos(query: String) {
        viewModelScope.launch {
            bibliotecaUiState = BibliotecaUiState.Loading
            try {
                val lista = librosRepository.getLibros(query)

                bibliotecaUiState = BibliotecaUiState.Success(lista)
            } catch (e: IOException) {
                e.printStackTrace()
                bibliotecaUiState = BibliotecaUiState.Error
            } catch (e: HttpException) {
                e.printStackTrace()
                bibliotecaUiState = BibliotecaUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                val application = (this[APPLICATION_KEY] as BibliotecaApplication)
                val librosRepository = application.container.librosRepository
                BibliotecaViewModel(librosRepository = librosRepository)
            }
        }
    }
}