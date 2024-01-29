package com.example.avatarthelastairbender.presentation.avatar_list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avatarthelastairbender.R
import com.example.avatarthelastairbender.domain.model.Avatar
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.navigation.AvatarTopAppBar
import com.example.avatarthelastairbender.ui.theme.AvatarTheLastAirBenderTheme

private val HighlightCardWidth = 170.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: AvatarListViewModel,
    onCharacterClick: (String, String) -> Unit
) {
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AvatarTopAppBar(
                title = stringResource(id = R.string.app_name),
                canNavigateBack = false
            )
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
            }
            else if (state.error.isNotBlank()) {
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
                MainScreen(state, onCharacterClick = onCharacterClick)
            }
        }
    }
}

@Composable
fun MainScreen(
    state: MainScreenState,
    onCharacterClick: (String, String) -> Unit
) {

    state.characters.airBendersList.map {
        it.category = "Air"
    }
    state.characters.fireBendersList.map {
        it.category = "Fire"
    }
    state.characters.waterBendersList.map {
        it.category = "Water"
    }
    state.characters.earthBendersList.map {
        it.category = "Earth"
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Air Nation",
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        state.characters.airBendersList.let {
            CharactersList(
                characters = it,
                onCharacterClick = onCharacterClick,
                category = "Air"
            )
        }
        Text(
            text = "Earth Nation",
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        state.characters.earthBendersList.let {
            CharactersList(
                characters = it,
                onCharacterClick = onCharacterClick,
                category = "Earth"
            )
        }
        Text(
            text = "Water Nation",
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        state.characters.waterBendersList.let {
            CharactersList(
                characters = it,
                onCharacterClick = onCharacterClick,
                category = "Water"
            )
        }
        Text(
            text = "Fire Nation",
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        state.characters.fireBendersList.let {
            CharactersList(
                characters = it,
                onCharacterClick = onCharacterClick,
                category = "Fire"
            )
        }
        Text(
            text = "Avatars",
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
        state.characters.avatarsList.let {
            AvatarList(
                avatars = it,
                onCharacterClick = onCharacterClick
            )
        }
    }
}


@Composable
fun CharacterImage(
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
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun CharacterItem(
    character: CharacterAffiliation,
    onCharacterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    gradient: List<Color>
) {

    Card(
        modifier = modifier
            .size(
                width = HighlightCardWidth,
                height = 220.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(onClick = {
                    character.category?.let {
                        onCharacterClick(
                            character._id,
                            it
                        )
                    }
                })
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            gradient[0],
                            gradient[1]
                        )
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth()
                )
                CharacterImage(
                    imageUrl = character.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = character.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                //color = MaterialTheme,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun AvatarItem(
    avatar: Avatar,
    onCharacterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    gradient: List<Color>
) {

    Card(
        modifier = modifier
            .size(
                width = HighlightCardWidth,
                height = 250.dp
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                //.clickable(onClick = { onCharacterClick(avatar._id,avatar.) })
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            gradient[0],
                            gradient[1]
                        )
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                )
                CharacterImage(
                    imageUrl = avatar.photoUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = avatar.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                //color = MaterialTheme,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = avatar.weapon,
                style = MaterialTheme.typography.body1,
                //color = Theme.colors.textHelp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun CharactersList(
    characters: List<CharacterAffiliation>,
    onCharacterClick: (String, String) -> Unit,
    category: String
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(characters) { index, character ->

            CharacterItem(
                character = character,
                onCharacterClick = onCharacterClick,
                gradient = when (category) {
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
        }
    }
}

@Composable
fun AvatarList(
    avatars: List<Avatar>,
    onCharacterClick: (String, String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        itemsIndexed(avatars) { _, avatar ->

            AvatarItem(
                avatar = avatar,
                onCharacterClick = onCharacterClick,
                gradient = listOf(Color(0xFF26C6DA), Color.White)
            )
        }
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun SnackCardPreview() {
    AvatarTheLastAirBenderTheme {
        val allies = listOf("Zuko", "Katara")
        val enemies = listOf("Azula", "Ozai")
        val character = CharacterAffiliation(
            "",
            "Fire",
            allies,
            enemies,
            "Chong",
            "https://vignette.wikia.nocookie.net/avatar/images/f/f8/Chong.png/revision/latest?cb=20140127210142",
            category = null
        )
        val gradient = listOf(Color.Blue, Color.White)
        CharacterItem(
            character = character,
            onCharacterClick = { characterId, characterName ->
                println("Character clicked! ID: $characterId, Name: $characterName")
            },
            gradient = gradient
        )
    }
}

@Preview("default")
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("large font", fontScale = 2f)
@Composable
fun CharacterListPreview() {
    AvatarTheLastAirBenderTheme {
        val allies = listOf("Zuko", "Katara")
        val enemies = listOf("Azula", "Ozai")
        val character = CharacterAffiliation(
            "",
            "Fire",
            allies,
            enemies,
            "Chong",
            "https://vignette.wikia.nocookie.net/avatar/images/f/f8/Chong.png/revision/latest?cb=20140127210142",
            category = null
        )
        CharactersList(
            characters = listOf(character, character),
            onCharacterClick = { characterId, characterName ->
                println("Character clicked! ID: $characterId, Name: $characterName")
            },
            category = "Fire"
        )
    }
}