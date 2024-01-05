package com.example.avatarthelastairbender.domain.usecases.getAllCharacters

import android.util.Log
import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.model.Avatar
import com.example.avatarthelastairbender.domain.model.CharacterAffiliation
import com.example.avatarthelastairbender.domain.model.toAvatar
import com.example.avatarthelastairbender.domain.model.toCharacterAffiliation
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: AvatarRepository
) {
    operator fun invoke(): Flow<Resource<MainScreenListViewState>> = flow {
        try {
            emit(Resource.Loading())

            val earthBenders = repository.getCharactersByAffiliation("Earth")
            val waterBenders = repository.getCharactersByAffiliation("Water")
            Log.d("WaterBenders", "invoke: $waterBenders")
            val fireBenders = repository.getCharactersByAffiliation("Fire")
            val avatars = repository.getAvatars()
            Log.d("Avatars", "invoke: $avatars")

            MainScreenListViewState(
                earthBendersList = earthBenders.map { it.toCharacterAffiliation() },
                waterBendersList = waterBenders.map { it.toCharacterAffiliation() },
                fireBendersList = fireBenders.map { it.toCharacterAffiliation() },
                avatarsList = avatars.map { it.toAvatar() },
            )
            emit(Resource.Success(MainScreenListViewState()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("couldn't reach server. Check your internet connection"))
        }
    }
}

data class MainScreenListViewState(
    val earthBendersList: List<CharacterAffiliation> = emptyList(),
    val waterBendersList: List<CharacterAffiliation> = emptyList(),
    val fireBendersList: List<CharacterAffiliation> = emptyList(),
    val avatarsList: List<Avatar> = emptyList()
)