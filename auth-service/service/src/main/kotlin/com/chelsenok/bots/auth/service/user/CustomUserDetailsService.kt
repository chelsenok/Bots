package com.chelsenok.bots.auth.service.user


import com.chelsenok.bots.auth.service.CustomUserDetails
import com.chelsenok.bots.auth.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService
@Autowired
constructor(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val user = userRepository.findByLogin(login)
        return CustomUserDetails(user)
    }
}