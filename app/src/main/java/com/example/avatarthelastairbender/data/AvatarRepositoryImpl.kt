package com.example.avatarthelastairbender.data

import com.example.avatarthelastairbender.data.remote.AvatarApi
import com.example.avatarthelastairbender.data.remote.dto.AvatarDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterAffiliationDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDetailDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDto
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import javax.inject.Inject

class AvatarRepositoryImpl @Inject constructor(private val api: AvatarApi): AvatarRepository{
    override suspend fun getCharacters(): List<CharacterDto> {
        return api.getAllCharacters()
    }

    override suspend fun getCharacterById(characterId: String): CharacterDetailDto {
        return api.getCharacterById(characterId)
    }

    override suspend fun getAvatars(): List<AvatarDto> {
        return api.getAllAvatars()
    }

    override suspend fun getCharactersByAffiliation(nation: String): List<CharacterAffiliationDto> {
        return api.getCharacterByAffiliation(nation)
    }

}