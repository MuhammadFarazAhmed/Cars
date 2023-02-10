package com.sevenpeakssoftware.faraz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sevenpeakssoftware.base.Toolbar
import com.sevenpeakssoftware.home.ui.HomeScreen
import com.sevenpeakssoftware.base.theme.CarsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val isLoading = MutableLiveData(true)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                lifecycleScope.launch {
                    delay(2000)
                    isLoading.value = false
                }
                isLoading.value!!
            }
        
        }
        super.onCreate(savedInstanceState)
        
        setContent {
            
            val systemUiController = rememberSystemUiController()
            val navController = rememberNavController()
            
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Black, darkIcons = false)
            }
    
            CarsTheme {
                Scaffold(topBar = { Toolbar() }, content = { padding ->
            
                    NavHost(navController,
                            startDestination = "home",
                            modifier = Modifier.padding(padding).fillMaxSize()) {
                
                        composable("home") { HomeScreen() }
                
                    }
                }, bottomBar = {})
            }
        }
    }
}
