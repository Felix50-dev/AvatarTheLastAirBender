package com.example.avatarthelastairbender.data.remote

import com.example.avatarthelastairbender.data.remote.dto.AvatarDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterAffiliationDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDetailDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AvatarApi {

    @GET("/api/v1/characters")
    suspend fun getAllCharacters(): List<CharacterDto>

    // TODO: confirm this
    @GET("/api/v1/characters/{characterId}")
    suspend fun getCharacterById(@Path ("characterId") character_id: String): CharacterDetailDto

    @GET("/api/v1/characters/avatar")
    suspend fun getAllAvatars(): List<AvatarDto>

    @GET("/api/v1/characters/")
    suspend fun getCharacterByAffiliation(@Query("affiliation") nation: String): List<CharacterAffiliationDto>





}