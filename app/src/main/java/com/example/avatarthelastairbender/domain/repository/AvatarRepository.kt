package com.example.avatarthelastairbender.domain.repository

import com.example.avatarthelastairbender.data.remote.dto.AvatarDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterAffiliationDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDetailDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDto

interface AvatarRepository {

    suspend fun getCharacters(): List<CharacterDto>

    suspend fun getCharacterById(characterId: String): CharacterDetailDto

    suspend fun getAvatars(): List <AvatarDto>

    suspend fun getCharactersByAffiliation(): List<CharacterAffiliationDto>

}