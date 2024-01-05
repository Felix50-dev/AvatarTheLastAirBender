package com.example.avatarthelastairbender.domain.model

import com.example.avatarthelastairbender.data.remote.dto.AvatarDto

data class Avatar(
    val _id: String,
    val affiliation: String? = "",
    val allies: List<String>,
    val enemies: List<String>,
    val gender: String,
    val name: String,
    val photoUrl: String,
    val position: String,
    val profession: String,
    val weapon: String
)

fun AvatarDto.toAvatar(): Avatar {
    return Avatar(
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
