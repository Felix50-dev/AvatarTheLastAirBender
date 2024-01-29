package com.example.avatarthelastairbender.presentation.avatar_list

import com.example.avatarthelastairbender.domain.usecases.getAllCharacters.MainScreenListViewState

data class MainScreenState(
    val isLoading: Boolean = false,
    val characters: MainScreenListViewState = MainScreenListViewState(),
    val error: String = ""
)
