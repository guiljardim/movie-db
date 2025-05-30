package com.jardimtech.movie_db.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.jardimtech.movie_db.presentation.SplashScreen
import com.jardimtech.movie_db.presentation.home.HomeScreen

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val DETAILS = "details/{movieId}"
    fun details(movieId: Int) = "details/$movieId"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(
            route = Routes.DETAILS,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            movieId?.let {
                //MovieDetailsScreen(navController, movieId)
            }
        }
    }
}