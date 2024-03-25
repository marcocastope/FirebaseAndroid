package com.marcocastope.firebaseyoutubeandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.marcocastope.firebaseyoutubeandroid.ui.screens.HomeScreen
import com.marcocastope.firebaseyoutubeandroid.ui.screens.RegisterScreen
import com.marcocastope.firebaseyoutubeandroid.ui.theme.FirebaseYoutubeAndroidTheme
import com.marcocastope.firebaseyoutubeandroid.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = AuthViewModel()
        setContent {
            FirebaseYoutubeAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val currentUser = authViewModel.currentUser.collectAsState()
                    if (currentUser.value != null) {
                        HomeScreen { authViewModel.logout() }
                    } else {
                        RegisterScreen(
                            onLoginClick = authViewModel::loginUser,
                            onRegisterClick = authViewModel::registerUser
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        authViewModel.listenToAuthState()
    }
}