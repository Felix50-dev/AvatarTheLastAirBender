package com.example.avatarthelastairbender.domain.usecases.getcharacters

import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.model.Character
import com.example.avatarthelastairbender.domain.model.toCharacter
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: AvatarRepository
) {

    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val characters = repository.getCharacters()
            emit(Resource.Success(characters.map { it.toCharacter() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("couldn't reach server. Check your internet connection"))
        }
    }
}