package com.chelsenok.bots.service.user

import com.chelsenok.bots.service.dtos.CurrentUserDto

interface UserService {

    fun getCurrentUser(): CurrentUserDto?

    fun isCurrentUser(userId: Long): Boolean

}