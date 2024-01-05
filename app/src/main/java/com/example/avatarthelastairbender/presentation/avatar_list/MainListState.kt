package com.example.avatarthelastairbender.presentation.avatar_list

import com.example.avatarthelastairbender.domain.usecases.getAllCharacters.MainScreenListViewState

data class MainListState(
    val isLoading: Boolean = false,
    var characters: MainScreenListViewState? = null,
    val error: String = ""
)
