package com.example.movieapp.presentation

import android.view.Menu
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            HomeScreenTop()
        }
    ) { paddingValues ->

    }
}

@Composable
fun HomeScreenTop(){
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
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