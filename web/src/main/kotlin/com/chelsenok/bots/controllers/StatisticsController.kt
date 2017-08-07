package com.chelsenok.bots.controllers

import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.services.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StatisticsController {

    @Autowired
    lateinit var statisticsService: StatisticsService

    @PostMapping(value = "/stats")
    fun postVideo(@RequestBody video: VideoPost): ResponseEntity<Unit> {
        if (statisticsService.isVideoExist(video.v)) {
            return ResponseEntity.badRequest().body(null)
        } else {
            statisticsService.addVideo(video)
            return ResponseEntity.ok(null)
        }
    }

    @GetMapping(value = "/stats")
    fun getStats(@RequestParam v: String): ResponseEntity<List<StatInfoGet>> {
        if (statisticsService.isVideoExist(v)) {
            return ResponseEntity(statisticsService.getAllStatsInfoByVideoId(v), HttpStatus.OK)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}