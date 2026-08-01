package com.example.movieapp.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieapp.viewModels.MovieViewModel

@Composable
fun HomeScreen(
    viewModel: MovieViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            HomeScreenTop()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(viewModel.movies) { movie ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = movie.Title,
                            fontSize = 18.sp
                        )
                    },
                    leadingContent = {
                        AsyncImage(
                            model = movie.Poster,
                            contentDescription = "Обложка фильма ${movie.Title}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(width = 50.dp, height = 75.dp)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun HomeScreenTop() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Главная",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(Icons.Default.MoreVert, contentDescription = "Больше")
    }
}
