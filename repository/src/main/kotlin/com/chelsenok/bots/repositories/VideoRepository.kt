package com.chelsenok.bots.repositories

import com.chelsenok.bots.entities.Video
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video, String>
