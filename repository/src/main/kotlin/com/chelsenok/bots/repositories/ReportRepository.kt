package com.chelsenok.bots.repositories

import com.chelsenok.bots.entities.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatRepository : JpaRepository<Report, Long>, StatCustom

interface StatCustom {
    fun findAllByVideoId(videoId: String): List<Report>

}
