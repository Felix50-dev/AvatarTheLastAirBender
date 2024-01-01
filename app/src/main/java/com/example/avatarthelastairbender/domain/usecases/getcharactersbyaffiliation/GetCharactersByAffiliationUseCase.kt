package com.example.avatarthelastairbender.domain.usecases.getcharactersbyaffiliation

import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.domain.model.toCharacterAffiliation
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersByAffiliationUseCase @Inject constructor(
    private val repository: AvatarRepository
) {

    operator fun invoke(nation: String): Flow<List<CharacterAffiliation>> = flow {
        val charactersByAffiliation = repository.getCharactersByAffiliation(nation)
        emit(charactersByAffiliation.map { it.toCharacterAffiliation() })
    }

}