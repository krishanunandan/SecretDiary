package com.krishanu.secretdiary.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.krishanu.secretdiary.navigation.NavigationScreen
import com.krishanu.secretdiary.ui.component.AppBarWithIconAndTitle
import com.krishanu.secretdiary.ui.component.DrawerContent
import com.krishanu.secretdiary.ui.component.HomeContent
import com.krishanu.secretdiary.ui.theme.SecretDiaryTheme
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    SecretDiaryTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DrawerContent()
                }) {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    AppBarWithIconAndTitle(
                        modifier = Modifier
                            .fillMaxWidth(), "Secret Diary", Icons.Filled.Menu
                    ) {
                        scope.launch {
                            if (drawerState.isClosed) {
                                drawerState.open()
                            } else {
                                drawerState.close()
                            }
                        }
                    }
                }, floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(
                                NavigationScreen.CreateNote.passArgs(
                                    noteID = 0,
                                    type = "Add",
                                    title = "",
                                    description = ""
                                )
                            )
                        },
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }) { innerPadding ->
                    HomeContent(navController = navController, paddingValues = innerPadding)
                }
            }
        }

    }

}