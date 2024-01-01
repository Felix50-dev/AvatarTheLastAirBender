package com.example.avatarthelastairbender.domain.usecases.getavatars

import com.example.avatarthelastairbender.domain.model.Avatar
import com.example.avatarthelastairbender.domain.model.toAvatar
import com.example.avatarthelastairbender.domain.repository.AvatarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvatarsUseCase @Inject constructor(
    private val repository: AvatarRepository
) {
    operator fun invoke(): Flow<List<Avatar>> = flow {
            val avatars = repository.getAvatars()
            emit(avatars.map { it.toAvatar() })
    }
}