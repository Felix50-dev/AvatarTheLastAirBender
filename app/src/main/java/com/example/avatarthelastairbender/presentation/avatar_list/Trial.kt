package com.example.avatarthelastairbender.presentation.avatar_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.domain.model.CharacterDetail

@Composable
fun TwoColorSurface(
    topColor: Color,
    bottomColor: Color,
    characterAffiliation: CharacterAffiliation
) {
    // Create a Column with two colored Surface components
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        topColor,
                        bottomColor
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(topColor)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(bottomColor)
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
                CharacterPhoto(imageUrl = characterAffiliation.photoUrl, contentDescription = "")
                CharacterInfo(character = characterAffiliation)
            }
        }
    }
}

@Preview
@Composable
fun TwoColorSurfaceWithContentScreenPreview() {
    val allies = listOf("Zuko", "Katara")
    val enemies = listOf("Azula", "Ozai")
    val character = CharacterAffiliation(
        "",
        "Fire",
        allies,
        enemies,
        "Chong",
        "https://vignette.wikia.nocookie.net/avatar/images/f/f8/Chong.png/revision/latest?cb=20140127210142"
    )
    TwoColorSurface(Color.Blue, Color.White, character)
}