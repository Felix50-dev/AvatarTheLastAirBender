package com.example.avatarthelastairbender.presentation.character_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avatarthelastairbender.common.Constants
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.usecases.getcharacter.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class     CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    val state: State<CharacterDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_CHARACTER_ID)?.let { characterId ->
            getCharacter(characterId)
        }
    }
    private fun getCharacter(characterId: String) {
        getCharacterUseCase(characterId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterDetailState(character = result.data)
                }
                is Resource.Error -> {
                    _state.value = CharacterDetailState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = CharacterDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}