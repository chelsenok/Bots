package com.chelsenok.bots.statistics.repository

import com.chelsenok.bots.statistics.repository.entities.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video, String>
