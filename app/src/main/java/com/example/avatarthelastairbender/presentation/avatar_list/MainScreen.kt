package com.example.avatarthelastairbender.presentation.avatar_list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avatarthelastairbender.R
import com.example.avatarthelastairbender.domain.model.Avatar
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.ui.theme.AvatarTheLastAirBenderTheme

private val HighlightCardWidth = 170.dp
private val HighlightCardPadding = 16.dp
private val Density.cardWidthWithPaddingPx
    get() = (HighlightCardWidth + HighlightCardPadding).toPx()

@Composable
fun MainScreen(
    viewModel: AvatarListViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        viewModel.state.value.avatarsList?.data?.let {
            AvatarList(
                avatars = it,
                onCharacterClick = {})
        }
        viewModel.state.value.earthBendersList?.data?.let {
            CharactersList(
                characters = it,
                onCharacterClick = {},
                category = "Earth"
            )
        }
        viewModel.state.value.waterBendersList?.data?.let {
            CharactersList(
                characters = it,
                onCharacterClick = {},
                category = "Water"
            )
        }
        viewModel.state.value.fireBendersList?.data?.let {
            CharactersList(
                characters = it,
                onCharacterClick = {},
                category = "Fire"
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
    onCharacterClick: (String) -> Unit,
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
                .clickable(onClick = { onCharacterClick(character._id) })
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
            Text(
                text = character.affiliation,
                style = MaterialTheme.typography.body1,
                //color = Theme.colors.textHelp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun AvatarItem(
    avatar: Avatar,
    onCharacterClick: (String) -> Unit,
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
                .clickable(onClick = { onCharacterClick(avatar._id) })
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
    onCharacterClick: (String) -> Unit,
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
                        listOf(Color.Yellow, Color(0xFF26C6DA))
                    }

                    else -> {
                        listOf(Color.Green, Color(0xFF964B00))
                    }
                }
            )
        }
    }
}

@Composable
fun AvatarList(
    avatars: List<Avatar>,
    onCharacterClick: (String) -> Unit
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

fun Modifier.offsetGradientBackground(
    colors: List<Color>,
    width: Density.() -> Float,
    offset: Density.() -> Float = { 0f }
) = drawBehind {
    val actualOffset = offset()

    drawRect(
        Brush.horizontalGradient(
            colors = colors,
            startX = -actualOffset,
            endX = width() - actualOffset,
            tileMode = TileMode.Mirror
        )
    )
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
            "https://vignette.wikia.nocookie.net/avatar/images/f/f8/Chong.png/revision/latest?cb=20140127210142"
        )
        val gradient = listOf(Color.Blue, Color.White)
        CharacterItem(
            character = character,
            onCharacterClick = { },
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
            "https://vignette.wikia.nocookie.net/avatar/images/f/f8/Chong.png/revision/latest?cb=20140127210142"
        )
        CharactersList(
            characters = listOf(character, character),
            onCharacterClick = {},
            category = "Fire"
        )
    }
}