package com.example.avatarthelastairbender.presentation.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avatarthelastairbender.presentation.avatar_list.CharacterPhoto


@Composable
fun TwoColorSurfaceEarth(
    colors: List<Color>,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val character = viewModel.state.value.character
    // Create a Column with two colored Surface components
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = colors
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