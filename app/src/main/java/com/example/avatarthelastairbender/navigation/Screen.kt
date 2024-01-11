package com.example.avatarthelastairbender.navigation

sealed class Screen(val route: String) {
    object CharactersListScreen: Screen("character_list_screen")
    object CharacterDetailScreen: Screen("character_detail_screen")
}