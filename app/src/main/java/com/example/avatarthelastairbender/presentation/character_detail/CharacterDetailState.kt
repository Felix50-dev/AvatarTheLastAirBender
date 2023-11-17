package com.example.avatarthelastairbender.presentation.character_detail

import com.example.avatarthelastairbender.domain.model.CharacterDetail

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: CharacterDetail? = null,
    val error: String = ""
)
