package com.chelsenok.bots.auth.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.chelsenok.bots.auth.repository.entities.User

class CustomUserDetails(private val user: User): UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> =
            listOf(SimpleGrantedAuthority("ROLE_" + user.role))

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = user.login

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = user.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}
