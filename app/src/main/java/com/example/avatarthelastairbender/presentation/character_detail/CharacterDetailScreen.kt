package com.example.avatarthelastairbender.presentation.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avatarthelastairbender.R


@Composable
fun CharacterDetails(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val character = viewModel.state.value.character
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = when (character?.category) {
                        "Fire" -> {
                            listOf(Color.Red, Color(0xFFFF7043))
                        }

                        "Water" -> {
                            listOf(Color.Blue, Color.White)
                        }

                        "Air" -> {
                            listOf(Color.Yellow, Color(0xFFFF7043))
                        }

                        else -> {
                            listOf(Color(0xFF964B00), Color.Green)
                        }
                    }
                )
            )
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        )
        // Central Box with content
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Transparent)
                .align(Alignment.CenterHorizontally),
            color = Color.Transparent// Set the background color to be transparent
        ) {
            // Content in the center
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (character != null) {
                    CharacterPhoto(imageUrl = character.photoUrl, contentDescription = "")
                }
                if (character != null) {
                    CharacterInformation(character = character)
                }
            }
        }
    }
}

@Composable
fun CharacterPhoto(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    Surface(
        color = Color.LightGray,
        elevation = elevation,
        shape = CircleShape,
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            placeholder = painterResource(R.drawable.chong),
            modifier = Modifier.size(240.dp),
            contentScale = ContentScale.Crop,
        )
    }
}