package com.example.avatarthelastairbender.presentation.avatar_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.model.Avatar
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.domain.usecases.getavatars.GetAvatarsUseCase
import com.example.avatarthelastairbender.domain.usecases.getcharactersbyaffiliation.GetCharactersByAffiliationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AvatarListViewModel"

@HiltViewModel
class AvatarListViewModel @Inject constructor(
    private val getCAvatarsUseCase: GetAvatarsUseCase,
    private val getCharactersByAffiliationUseCase: GetCharactersByAffiliationUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(MainScreenListViewState())
    val state: State<MainScreenListViewState> = _state

    init {
        viewModelScope.launch {
            val earthBenders = getCharactersByAffiliationUseCase("earth")
            val waterBenders = getCharactersByAffiliationUseCase("water")
            val fireBenders = getCharactersByAffiliationUseCase("fire")
            val avatars = getCAvatarsUseCase()

            combine(
                earthBenders,
                waterBenders,
                fireBenders,
                avatars
            ) { earthBendersList, waterBendersList, fireBendersList, avatarsList ->
                MainScreenListViewState(
                    earthBendersList,
                    waterBendersList,
                    fireBendersList,
                    avatarsList
                )
            }.collect { _state.value = it }
            Log.d(TAG, "onSuccess:  ${_state.value.avatarsList?.data}")
        }
    }
}

data class MainScreenListViewState(
    val earthBendersList: Resource<List<CharacterAffiliation>>? = null,
    val waterBendersList: Resource<List<CharacterAffiliation>>? = null,
    val fireBendersList: Resource<List<CharacterAffiliation>>? = null,
    val avatarsList: Resource<List<Avatar>>? = null
)