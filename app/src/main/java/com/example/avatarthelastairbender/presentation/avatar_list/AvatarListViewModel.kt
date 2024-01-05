package com.example.avatarthelastairbender.presentation.avatar_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.usecases.getAllCharacters.GetAllCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarListViewModel @Inject constructor(
    private val getCAvatarsUseCase: GetAllCharactersUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MainListState())
    val state: State<MainListState> = _state

    init {
        viewModelScope.launch {
            getAllCharacters()
        }
    }
    private fun getAllCharacters() {
        getCAvatarsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MainListState(characters = result.data)
                }
                is Resource.Error -> {
                    _state.value = MainListState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = MainListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}