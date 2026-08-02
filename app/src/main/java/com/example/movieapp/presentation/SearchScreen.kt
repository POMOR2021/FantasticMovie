package com.example.movieapp.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.keepScreenOn
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieapp.viewModels.MovieViewModel

@Composable
fun SearchScreen(
    movieViewModel: MovieViewModel = viewModel()
) {
    var text by remember { mutableStateOf("") }

    Column() {
        Text(
            text = "Поиск",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                movieViewModel.searchMovies(it)
            },
            placeholder = {
                Text("Введите название фильма")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(22.dp)
        )

        LazyColumn {
            items(movieViewModel.movies, key = {it.id}) { movie ->
                ListItem(
                    headlineContent = {
                        Text(movie.title ?: "Без названия")
                    },
                    leadingContent = {
                        AsyncImage(
                            model = movie.poster_path?.let{
                                "https://image.tmdb.org/t/p/w154$it"
                            },
                            contentDescription = "Обложка фильма ${movie.title}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.width(70.dp).height(100.dp)
                        )
                    },
                    supportingContent = {
                        Column{
                            RatingStars(movie.vote_average)

                            Text(
                                movie.vote_average.toString()
                            )
                        }
                    }
                )
            }
        }
    }
}
@Composable
fun RatingStars(rating: Double) {
    Row{
        repeat(10) { index ->
            Icon(
                imageVector = if (index < rating.toInt())
                    Icons.Default.Star
                else
                    Icons.Default.StarBorder,
                contentDescription = null,
                tint = Color.Yellow
            )
        }
    }
}