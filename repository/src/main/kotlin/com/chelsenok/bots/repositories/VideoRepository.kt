package com.chelsenok.bots.repositories

import com.chelsenok.bots.entities.Video
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : CrudRepository<Video, String>
