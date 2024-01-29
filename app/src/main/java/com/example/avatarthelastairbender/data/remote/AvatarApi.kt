package com.example.avatarthelastairbender.data.remote

import com.example.avatarthelastairbender.common.Constants
import com.example.avatarthelastairbender.data.remote.dto.AvatarDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterAffiliationDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDetailDto
import com.example.avatarthelastairbender.data.remote.dto.CharacterDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AvatarApi {

    @GET("/api/v1/characters")
    suspend fun getAllCharacters(): List<CharacterDto>

    // TODO: confirm this
    @GET("/api/v1/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") character_id: String): CharacterDetailDto

    @GET("/api/v1/characters/avatar")
    suspend fun getAllAvatars(): List<AvatarDto>

    @GET("/api/v1/characters/")
    suspend fun getCharacterByAffiliation(@Query("affiliation") nation: String): List<CharacterAffiliationDto>

    companion object {
        fun create(): AvatarApi {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AvatarApi::class.java)
        }
    }

}