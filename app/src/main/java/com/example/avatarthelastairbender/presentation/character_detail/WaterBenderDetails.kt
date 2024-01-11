package com.example.avatarthelastairbender.presentation.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.domain.model.CharacterDetail
import com.example.avatarthelastairbender.presentation.avatar_list.CharacterPhoto


@Composable
fun CharacterInformation(character: CharacterDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        character.name?.let {
            Text(
                it,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Right
            )
        }
        character.affiliation?.let { Text(it, fontSize = 16.sp, textAlign = TextAlign.Right) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            Text(character.allies.toString(), fontSize = 16.sp)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = Icons.Default.Place, contentDescription = null)
            Text(character.enemies.toString(), fontSize = 16.sp)
        }
    }
}

@Composable
fun TwoColorSurface(
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