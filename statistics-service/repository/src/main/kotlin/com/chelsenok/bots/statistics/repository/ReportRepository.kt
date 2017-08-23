package com.chelsenok.bots.statistics.repository

import com.chelsenok.bots.statistics.repository.entities.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository : JpaRepository<Report, Long>
