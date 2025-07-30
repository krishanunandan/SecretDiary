package com.krishanu.secretdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.krishanu.secretdiary.navigation.AppNavHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        val splashscreen = installSplashScreen()
        splashscreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(5000)
            keepSplashScreen = false
        }
        setContent {
            SecretDiaryApp()
        }
    }
}

@Composable
fun SecretDiaryApp() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}


