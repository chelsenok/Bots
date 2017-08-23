package com.chelsenok.bots.auth.service.user

import com.chelsenok.bots.auth.repository.UserRepository
import com.chelsenok.bots.auth.service.utils.SecurityUtils
import com.chelsenok.bots.auth.service.dtos.CurrentUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.modelmapper.ModelMapper

@Suppress("DEPRECATED_IDENTITY_EQUALS")
@Service
class UserServiceImpl
@Autowired
constructor(private val userRepository: UserRepository,
            private val securityUtils: SecurityUtils,
            private val modelMapper: ModelMapper) : UserService {

    override fun getCurrentUser(): CurrentUserDto? {
        val login = securityUtils.getCurrentUserLogin()
        return if (login != null) modelMapper.map(
                userRepository.findByLogin(login),
                CurrentUserDto::class.java
        )
        else null
    }

    override fun isCurrentUser(userId: Long): Boolean {
        val login = securityUtils.getCurrentUserLogin()
        return login != null && userRepository.findByLogin(login).id === userId
    }
}
