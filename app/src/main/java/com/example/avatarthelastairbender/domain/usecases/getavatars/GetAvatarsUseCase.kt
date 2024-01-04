package com.example.avatarthelastairbender.domain.usecases.getavatars

import com.example.avatarthelastairbender.common.Resource
import com.example.avatarthelastairbender.domain.model.Avatar
import com.example.avatarthelastairbender.domain.model.toAvatar
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAvatarsUseCase @Inject constructor(
    private val repository: AvatarRepository
) {
    operator fun invoke(): Flow<Resource<List<Avatar>>> = flow {
        try {
            emit(Resource.Loading())
            val avatars = repository.getAvatars()
            emit(Resource.Success(avatars.map { it.toAvatar() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("couldn't reach server. Check your internet connection"))
        }
    }
}