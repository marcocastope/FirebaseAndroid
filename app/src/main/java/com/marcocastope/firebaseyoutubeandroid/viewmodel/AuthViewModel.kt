package com.marcocastope.firebaseyoutubeandroid.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.marcocastope.firebaseyoutubeandroid.model.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _currentUser: MutableStateFlow<FirebaseUser?> = MutableStateFlow(auth.currentUser)
    val currentUser = _currentUser

    var profile by mutableStateOf(Profile())
        private set

    init {
        profile = profile.copy(
            displayName = _currentUser.value?.displayName ?: "",
            photoUri = _currentUser.value?.photoUrl
        )
    }

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                Log.d(javaClass.simpleName, "Usuario registrado con éxito")
            } catch (e: Exception) {
                Log.d(javaClass.simpleName, "${e.message}")
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
            } catch (e: Exception) {
                Log.d(javaClass.simpleName, "${e.message}")
            }
        }
    }

    fun logout() {
        try {
            auth.signOut()
        } catch (e: Exception) {
            Log.d(javaClass.simpleName, "${e.message}")
        }
    }

    fun listenToAuthState() {
        auth.addAuthStateListener {
            this._currentUser.value = it.currentUser
            profile = profile.copy(
                displayName = it.currentUser?.displayName ?: "",
                photoUri = it.currentUser?.photoUrl
            )
        }
    }

    fun updateProfileUser(displayName: String, photoUrl: Uri) {
        auth.currentUser?.let { user ->
            val updateRequest = userProfileChangeRequest {
                this.displayName = displayName
                this.photoUri = photoUrl
            }

            viewModelScope.launch {
                try {
                    user.updateProfile(updateRequest).await()
                    Log.d(javaClass.simpleName, "Usuario actualizado con  éxito")
                } catch (e: Exception) {
                    Log.d(javaClass.simpleName, "${e.message}")
                }
            }
            profile = profile.copy(displayName = displayName, photoUri = photoUrl)
        }
    }
}
