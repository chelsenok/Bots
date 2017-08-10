package com.chelsenok.bots.repository

import com.chelsenok.bots.repository.entities.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video, String>
