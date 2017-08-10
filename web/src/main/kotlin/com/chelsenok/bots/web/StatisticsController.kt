package com.chelsenok.bots.web

import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost
import com.chelsenok.bots.service.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StatisticsController {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    @PostMapping(value = "/reports")
    fun postVideo(@RequestBody video: VideoPost): ResponseEntity<Unit> {
        if (statisticsService.isVideoValid(video.id)) {
            statisticsService.addVideo(video)
            return ResponseEntity.ok(null)
        } else {
            return ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping(value = "/reports")
    fun getStats(@RequestParam id: String): ResponseEntity<List<StatInfoGet>> {
        if (statisticsService.isVideoExists(id)) {
            return ResponseEntity(statisticsService.getAllStatsInfoByVideoId(id), HttpStatus.OK)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}