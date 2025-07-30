package com.krishanu.secretdiary.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.krishanu.secretdiary.ui.screens.AddUpdateNoteScreen
import com.krishanu.secretdiary.ui.screens.HomeScreen

import com.krishanu.secretdiary.ui.screens.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationScreen.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationScreen.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavigationScreen.Home.route) {
            HomeScreen(navController)
        }

        composable(
            route = NavigationScreen.CreateNote.route,
            arguments = listOf(
                navArgument("noteID") { type = NavType.IntType },
                navArgument("type") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType })
        ) { backStackEntry ->
            val noteID = backStackEntry.arguments?.getInt("noteID")
            val type = backStackEntry.arguments?.getString("type")
            val title = backStackEntry.arguments?.getString("title")
            val description = backStackEntry.arguments?.getString("description")
            AddUpdateNoteScreen(navController, type!!, noteID!!, title!!, description!!)
        }
    }
}