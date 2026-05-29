//creado por Edwin Mauricio Morales Rodriguez
package com.example.libreria.data

import com.example.libreria.internet.LibrosApiService

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val jsonConfig = Json {
        ignoreUnknownKeys = true // Esto evita que la app truene si Google manda datos extra
        coerceInputValues = true
    }

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(jsonConfig.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: LibrosApiService by lazy {
        retrofit.create(LibrosApiService::class.java)
    }

    override val librosRepository: LibrosRepository by lazy {
        NetworkLibrosRepository(retrofitService)
    }
}