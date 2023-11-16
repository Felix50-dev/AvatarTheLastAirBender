package com.example.avatarthelastairbender.data.remote.dto

data class CharacterAffiliationDto(
    val _id: String,
    val affiliation: String,
    val allies: List<String>,
    val enemies: List<String>,
    val name: String,
    val photoUrl: String
)