package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.presentation.DetailsScreen
import com.example.movieapp.presentation.FavoriteScreen
import com.example.movieapp.presentation.HomeScreen
import com.example.movieapp.presentation.SearchScreen
import com.example.movieapp.ui.theme.MovieAppTheme


enum class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    Home("home", "Главная", Icons.Default.Home),
    Search("search", "Поиск", Icons.Default.Search),
    Favorite("favorite", "Любимое", Icons.Default.Favorite),
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme() {
                MyNavigationApp()
            }
        }
    }

    @Composable
    fun MyNavigationApp() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Scaffold(
            bottomBar = {
                NavigationBar {
                    BottomNavItem.entries.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true

                                        restoreState = true
                                    }

                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = { Text(item.title) }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                modifier = Modifier
                    .padding(paddingValues),
                navController = navController,
                startDestination = "home"
            ) {
                composable(BottomNavItem.Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(BottomNavItem.Search.route) {
                    SearchScreen()
                }
                composable(BottomNavItem.Favorite.route) {
                    FavoriteScreen()
                }
                composable("details") {
                    DetailsScreen()
                }
            }
        }
    }
}

