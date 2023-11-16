package com.example.avatarthelastairbender.data.remote.dto

data class CharacterDto(
    val _id: String,
    val allies: List<String>,
    val enemies: List<Any>,
    val name: String,
    val photoUrl: String
)