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

    init {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchMovies(
                    apiKey = "8cb8bc6",
                    search = "spider"
                )

                if (response.Search != null) {
                    movies = response.Search
                    Log.d("TMDB", "Успешно загружено фильмов: ${movies.size}")
                } else {
                    Log.e("TMDB", "Сервер вернул пустой список Search")
                }
            } catch (e: Exception) {
                Log.e("TMDB", "Ошибка сети: ${e.message ?: "Error"}")
            }
        }
    }
}
