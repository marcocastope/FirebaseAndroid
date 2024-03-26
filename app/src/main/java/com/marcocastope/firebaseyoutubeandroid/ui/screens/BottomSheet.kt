package com.marcocastope.firebaseyoutubeandroid.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marcocastope.firebaseyoutubeandroid.ui.theme.FirebaseYoutubeAndroidTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    dismiss: () -> Unit,
    onUpdateProfile: (String, Uri) -> Unit,
    onLogoutClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var name by remember {
        mutableStateOf("")
    }
    val onNameChange: (String) -> Unit = {
        name = it
    }

    var photo by remember {
        mutableStateOf("")
    }
    val onPhotoChange: (String) -> Unit = {
        photo = it
    }
    ModalBottomSheet(
        onDismissRequest = dismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = name, onValueChange = onNameChange, label = {
                Text(text = "Username")
            })
            OutlinedTextField(value = photo, onValueChange = onPhotoChange, label = {
                Text(text = "Foto")
            })

            Button(onClick = {
                val photoUriUpdate = Uri.parse(photo)
                onUpdateProfile(name, photoUriUpdate)
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) dismiss()
                }
            }) {
                Text(text = "Actualizar Perfil")
            }

            Button(onClick = {
                onLogoutClick()
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) dismiss()
                }
            }) {
                Text(text = "Cerrar sesión")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun BottomSheetPrev() {
    FirebaseYoutubeAndroidTheme {
        BottomSheet(dismiss = { /*TODO*/ }, onUpdateProfile = { _, _ -> }) {

        }
    }
}