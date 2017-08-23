package com.chelsenok.bots.auth.repository

import com.chelsenok.bots.auth.repository.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>, UserCustom {

    @Query("SELECT u FROM User u WHERE LOWER(u.login) LIKE CONCAT('%', LOWER(:part), '%')")
    fun getUsersByConsists(@Param("part") s: String): List<User>

}

interface UserCustom {
    fun findByLogin(login: String): User
}