package com.chelsenok.bots.controllers

import com.chelsenok.bots.dtos.StatInfo
import com.chelsenok.bots.services.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StatisticsController {

    @Autowired
    lateinit var service: StatisticsService

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun hello(): List<StatInfo> = service.getAllStatsInfoByVideoId("la")
}