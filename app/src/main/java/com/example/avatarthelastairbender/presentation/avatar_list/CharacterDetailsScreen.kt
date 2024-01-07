package com.example.avatarthelastairbender.presentation.avatar_list

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avatarthelastairbender.R
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.ui.theme.AvatarTheLastAirBenderTheme

@Composable
fun CharacterDetailsScreen(
    character: CharacterAffiliation,
    colors: List<Color>
) {
    DualColorSurface(colors[0], colors[1]) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CharacterPhoto(character.photoUrl, "")
            Spacer(modifier = Modifier.height(16.dp))
            CharacterInfo(character)
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
fun CharacterInfo(character: CharacterAffiliation) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(character.name, fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Right)
        Text(character.affiliation, fontSize = 16.sp, textAlign = TextAlign.Right)
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
fun DualColorSurface(
    color1: Color,
    color2: Color,
    content: @Composable (Modifier) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(color1)
        ) {

        }
        Box(
            modifier = Modifier
                .weight(1f)
                .background(color2)
        ) {
            // Empty box for the second half background color
        }
        content(Modifier.weight(1f))
    }
}

@Preview()
@Composable
fun CharacterDetailsScreenPreview() {
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
        val colors = listOf(Color.White, Color.Blue)
        CharacterDetailsScreen(character, colors)
    }
}