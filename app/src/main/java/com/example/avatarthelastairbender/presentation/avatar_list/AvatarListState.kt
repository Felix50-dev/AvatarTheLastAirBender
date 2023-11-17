package com.example.avatarthelastairbender.presentation.avatar_list

import com.example.avatarthelastairbender.domain.model.Avatar

data class AvatarListState(
    val isLoading: Boolean = false,
    val avatars: List<Avatar>? = null,
    val error: String = ""
)
