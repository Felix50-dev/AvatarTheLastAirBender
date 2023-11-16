package com.example.avatarthelastairbender.domain.model

import com.example.avatarthelastairbender.data.remote.dto.CharacterDetailDto

data class CharacterDetail(
    val _id: String,
    val affiliation: String,
    val allies: List<String>,
    val enemies: List<String>,
    val gender: String,
    val name: String,
    val photoUrl: String,
    val position: String,
    val profession: String,
    val weapon: String
)

fun CharacterDetailDto.toCharacterDetail(): CharacterDetail {
    return CharacterDetail(
        _id = _id,
        affiliation = affiliation,
        allies = allies,
        enemies = enemies,
        gender = gender,
        name = name,
        photoUrl = photoUrl,
        position = position,
        profession = profession,
        weapon = weapon
    )
}


