package com.example.avatarthelastairbender.presentation.avatar_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.usecases.getavatars.GetAvatarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AvatarListViewModel @Inject constructor(
    private val getCAvatarsUseCase: GetAvatarsUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AvatarListState())
    val state: State<AvatarListState> = _state

    init {
        getAvatars()
    }


    private fun getAvatars() {
        getCAvatarsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AvatarListState(avatars = result.data)
                }
                is Resource.Error -> {
                    _state.value = AvatarListState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = AvatarListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}