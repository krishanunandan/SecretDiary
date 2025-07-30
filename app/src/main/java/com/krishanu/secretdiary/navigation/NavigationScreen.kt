package com.krishanu.secretdiary.navigation

sealed class NavigationScreen(val route: String) {
    object Splash : NavigationScreen(Screen.Splash.name)
    object Home : NavigationScreen(Screen.Home.name)
    object CreateNote :
        NavigationScreen("${Screen.CreateNote.name}?noteID={noteID}&type={type}&title={title}&description={description}") {
        fun passArgs(noteID: Int, type: String, title: String, description: String): String {
            return "${Screen.CreateNote.name}?noteID=$noteID&type=$type&title=$title&description=$description"
        }
    }

    object Settings : NavigationScreen(Screen.Settings.name)
    object AppLock : NavigationScreen(Screen.AppLock.name)
}