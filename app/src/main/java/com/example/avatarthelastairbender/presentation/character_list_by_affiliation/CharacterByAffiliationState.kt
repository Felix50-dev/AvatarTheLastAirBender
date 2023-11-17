package com.example.avatarthelastairbender.presentation.character_list_by_affiliation

import com.example.avatarthelastairbender.domain.model.CharacterAffiliation

data class CharacterByAffiliationState(
    val isLoading: Boolean = false,
    val characters: List<CharacterAffiliation>? = null,
    val error: String = ""
)
