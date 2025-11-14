package com.example.authflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authflow.ui.home.HomeScreen
import com.example.authflow.ui.login.LoginScreen
import com.example.authflow.ui.splash.SplashScreen
import com.example.authflow.ui.theme.AuthFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthFlowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val nav = rememberNavController()

                        NavHost(navController = nav, startDestination = "splash") {
                            composable("splash") {
                                SplashScreen(
                                    toLogin = {
                                        nav.navigate("login") {
                                            popUpTo("splash") { inclusive = true }
                                        }
                                    },
                                    toHome = {
                                        nav.navigate("home") {
                                            popUpTo("splash") { inclusive = true }
                                        }
                                    }
                                )
                            }
                            composable("login") {
                                LoginScreen(
                                    onSuccess = {
                                        nav.navigate("home") {
                                            popUpTo("login") { inclusive = true } // remove Login from back stack
                                        }
                                    }
                                )
                            }
                            composable("home") { HomeScreen() }
                        }
                    }
                }
            }
        }
    }
}