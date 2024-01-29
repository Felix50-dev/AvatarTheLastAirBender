package com.example.avatarthelastairbender.presentation.character_detail

import android.util.Log
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

private const val TAG = "CharacterDetailViewMode"

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    val state: State<CharacterDetailState> = _state

    private val characterId: String = checkNotNull(savedStateHandle[Constants.PARAM_CHARACTER_ID])
    private val category: String = checkNotNull(savedStateHandle[Constants.PARAM_CHARACTER_CATEGORY])

    init {
        getCharacter(characterId)
    }

    private fun getCharacter(characterId: String) {
        getCharacterUseCase(characterId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterDetailState(character = result.data)
                    _state.value.character?.category = category
                    Log.d(TAG, "onCreate: detailSuccess: ${state.value.character?.enemies}")
                }

                is Resource.Error -> {
                    _state.value =
                        CharacterDetailState(error = result.message ?: "Unexpected error")
                    Log.d(TAG, "onCreate: detailError: ${state.value.error}")
                }

                is Resource.Loading -> {
                    _state.value = CharacterDetailState(isLoading = true)
                    Log.d(TAG, "onCreate: detailLoading: ${state.value.isLoading}")
                }
            }
        }.launchIn(viewModelScope)
    }
}