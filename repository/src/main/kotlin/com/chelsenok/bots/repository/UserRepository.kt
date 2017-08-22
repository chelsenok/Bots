package com.chelsenok.bots.repository

import com.chelsenok.bots.repository.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>, UserCustom

interface UserCustom {
    fun findByLogin(login: String): User
}