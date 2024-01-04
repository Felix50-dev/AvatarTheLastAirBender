package com.example.avatarthelastairbender.presentation.avatar_list

data class MainListState(
    val isLoading: Boolean = false,
    var characters: MainScreenListViewState? = null,
    val error: String = ""
)
