package com.example.movieapp.viewModels

import MovieDto
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    var movies by mutableStateOf<List<MovieDto>>(emptyList())
        private set

    var searchText by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            try {
                movies = RetrofitInstance.api.getPopularMovies().results
            } catch (e: Exception) {
                Log.e("TMDB", "Ошибка сети: ${e.message ?: "Error"}")
            }
        }


    }
    fun searchMovies(query: String) {
        searchText = query

        viewModelScope.launch {
            try {
                movies = if (query.isBlank()) {
                    RetrofitInstance.api.getPopularMovies().results
                } else {
                    RetrofitInstance.api.searchMovies(query).results
                }
            } catch (e: Exception) {
                Log.e("TMDB", e.toString())
            }
        }
    }
}
