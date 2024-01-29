package com.example.avatarthelastairbender.domain.usecases.getcharactersbyaffiliation

import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.domain.model.toCharacterAffiliation
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersByAffiliationUseCase @Inject constructor(
    private val repository: AvatarRepository
) {

    operator fun invoke(nation: String): Flow<Resource<List<CharacterAffiliation>>> = flow {
        try {
            emit(Resource.Loading())
            val charactersByAffiliation = repository.getCharactersByAffiliation(nation)
            emit(Resource.Success(data = charactersByAffiliation.map { it.toCharacterAffiliation() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("couldn't reach server. Check your internet connection"))
        }
    }

}