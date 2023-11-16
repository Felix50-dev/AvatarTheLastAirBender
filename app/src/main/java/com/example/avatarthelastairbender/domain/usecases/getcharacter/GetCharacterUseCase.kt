package com.example.avatarthelastairbender.domain.usecases.getcharacter

import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.model.CharacterDetail
import com.example.avatarthelastairbender.domain.model.toCharacterDetail
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: AvatarRepository
){

    operator fun invoke(characterId: String): Flow<Resource<CharacterDetail>> = flow {
        try {
            emit(Resource.Loading())
            val characterDetail = repository.getCharacterById(characterId).toCharacterDetail()
            emit(Resource.Success(characterDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("couldn't reach server. Check your internet connection"))
        }
    }
}