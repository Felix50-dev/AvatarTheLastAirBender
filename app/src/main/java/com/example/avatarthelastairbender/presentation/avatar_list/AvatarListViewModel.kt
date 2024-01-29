package com.example.avatarthelastairbender.presentation.avatar_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.usecases.getAllCharacters.GetAllCharactersUseCase
import com.example.avatarthelastairbender.domain.usecases.getAllCharacters.MainScreenListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AvatarListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getAllCharactersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MainScreenState(characters = result.data ?: MainScreenListViewState())
                }

                is Resource.Error -> {
                    _state.value = MainScreenState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MainScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}