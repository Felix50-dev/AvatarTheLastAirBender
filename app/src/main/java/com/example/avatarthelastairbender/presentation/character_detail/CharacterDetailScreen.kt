package com.example.avatarthelastairbender.presentation.character_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avatarthelastairbender.R
import com.example.avatarthelastairbender.domain.model.CharacterDetail
import com.example.avatarthelastairbender.navigation.AvatarTopAppBar

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            state.character?.name?.let {
                AvatarTopAppBar(
                    title = it,
                    canNavigateBack = true,
                    navigateUp = navigateBack
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Green
                )
            } else if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            } else {
                CharacterDetails(state = state)
            }
        }
    }
}


@Composable
fun CharacterDetails(
    state: CharacterDetailState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = when (state.character?.category) {
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
                if (state.character != null) {
                    CharacterPhoto(imageUrl = state.character.photoUrl, contentDescription = "")
                }
                if (state.character != null) {
                    CharacterInformation(character = state.character)
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

@Composable
fun CharacterInformation(character: CharacterDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        character.name?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "Name:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.alignByBaseline()
                )
                Text(
                    text = it,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.alignByBaseline()
                )
            }
        }
        character.affiliation?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Affiliation:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.alignByBaseline()
                )
                Text(text = it, fontSize = 16.sp, modifier = Modifier.alignByBaseline())
            }
        }
    }
    character.position?.let {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Position:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.alignByBaseline()
            )
            Text(text = it, fontSize = 16.sp, modifier = Modifier.alignByBaseline())
        }
    }
    character.weapon?.let {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Weapon:",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.alignByBaseline()
            )
            Text(text = it, fontSize = 16.sp, modifier = Modifier.alignByBaseline())
        }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = "Allies:",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.alignByBaseline()
        )
        Text(character.allies.toString(), fontSize = 16.sp, modifier = Modifier.alignByBaseline())
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Enemies:",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = character.enemies.toString(),
            fontSize = 16.sp,
            modifier = Modifier.alignByBaseline()
        )
    }
}


