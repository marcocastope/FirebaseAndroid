package com.marcocastope.firebaseyoutubeandroid.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.marcocastope.firebaseyoutubeandroid.R
import com.marcocastope.firebaseyoutubeandroid.model.Profile
import com.marcocastope.firebaseyoutubeandroid.ui.theme.FirebaseYoutubeAndroidTheme

@Composable
fun HomeScreen(
    profile: Profile,
    onLogoutClick: () -> Unit,
    onUpdateProfile: (String, Uri) -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {
                showBottomSheet = true
            }) {
                AsyncImage(
                    model = profile.photoUri,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Crop,
                    fallback = painterResource(id = R.drawable.ic_account_circle_24)
                )

            }
            Column {
                Text(text = "Hola, ${profile.displayName}")
            }
        }

        Text(
            text = "Inicio",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        if (showBottomSheet) {
            BottomSheet(
                dismiss = { showBottomSheet = false },
                onUpdateProfile = onUpdateProfile,
                onLogoutClick = onLogoutClick
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    FirebaseYoutubeAndroidTheme {
        HomeScreen(
            profile = Profile(),
            onLogoutClick = { }) { _, _ -> }
    }
}