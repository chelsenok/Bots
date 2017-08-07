package com.chelsenok.bots.repositories

import com.chelsenok.bots.entities.Stat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StatRepository : JpaRepository<Stat, Long>, StatCustom

interface StatCustom {
    fun findAllByVideoId(videoId: String): List<Stat>

}
