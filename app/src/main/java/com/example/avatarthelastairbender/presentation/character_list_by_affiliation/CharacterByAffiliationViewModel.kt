package com.example.avatarthelastairbender.presentation.character_list_by_affiliation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avatarthelastairbender.common.Constants
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.usecases.getcharactersbyaffiliation.GetCharactersByAffiliationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterByAffiliationViewModel @Inject constructor(
    private val getCharacterByAffiliationUseCase: GetCharactersByAffiliationUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CharacterByAffiliationState())
    val state: State<CharacterByAffiliationState> = _state

//    init {
//        savedStateHandle.get<String>(Constants.PARAM_CHARACTER_ID)?.let { nation ->
//            getCharactersByAffiliation(nation)
//        }
//    }
//    private fun getCharactersByAffiliation(nation: String) {
//        getCharacterByAffiliationUseCase(nation).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = CharacterByAffiliationState(characters = result.data)
//                }
//                is Resource.Error -> {
//                    _state.value = CharacterByAffiliationState(error = result.message ?: "Unexpected error")
//                }
//                is Resource.Loading -> {
//                    _state.value = CharacterByAffiliationState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}