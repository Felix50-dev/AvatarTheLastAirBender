package com.example.avatarthelastairbender.data.remote.dto

data class AvatarDto(
    val _id: String,
    val affiliation: String,
    val allies: List<String>,
    val enemies: List<String>,
    val first: String,
    val gender: String,
    val hair: String,
    val name: String,
    val photoUrl: String,
    val position: String,
    val profession: String,
    val weapon: String
)

