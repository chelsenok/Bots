package com.chelsenok.bots.application

import com.chelsenok.bots.service.StatisticsService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationTests {

    @Autowired
    private lateinit var statService: StatisticsService

    @Test
    fun test() {
        val list = statService.getUsersByConsists("ad")
        println("f")
    }

}
