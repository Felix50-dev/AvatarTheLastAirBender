package com.example.avatarthelastairbender.domain.model

import com.example.avatarthelastairbender.data.remote.dto.CharacterDto

data class Character(
    val _id: String,
    val allies: List<String>,
    val enemies: List<Any>,
    val name: String,
    val photoUrl: String
)

fun CharacterDto.toCharacter(): Character {
    return Character(
        _id = _id,
        allies = allies,
        enemies = enemies,
        name = name,
        photoUrl = photoUrl
    )
}