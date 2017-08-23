package com.chelsenok.bots.auth.service.user

import com.chelsenok.bots.auth.service.dtos.CurrentUserDto

interface UserService {

    fun getCurrentUser(): CurrentUserDto?

    fun isCurrentUser(userId: Long): Boolean

}