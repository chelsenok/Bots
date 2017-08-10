package com.chelsenok.bots.repository

import com.chelsenok.bots.repository.entities.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository : JpaRepository<Report, Long>, StatCustom

interface StatCustom {
    fun findAllByVideoId(videoId: String): List<Report>

}
