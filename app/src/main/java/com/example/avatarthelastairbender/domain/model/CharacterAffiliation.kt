package com.example.avatarthelastairbender.domain.model

import androidx.compose.ui.graphics.Color
import com.example.avatarthelastairbender.data.remote.dto.CharacterAffiliationDto

data class CharacterAffiliation(
    val _id: String,
    val affiliation: String,
    val allies: List<String>,
    val enemies: List<String>,
    val name: String,
    val photoUrl: String?,
    val category: String?,
)

fun CharacterAffiliationDto.toCharacterAffiliation(): CharacterAffiliation {
    return CharacterAffiliation(
        _id = _id,
        affiliation = affiliation,
        allies = allies,
        enemies = enemies,
        name = name,
        photoUrl = photoUrl,
        category = null
    )
}
