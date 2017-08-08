package com.chelsenok.bots

import com.chelsenok.bots.controllers.StatisticsController
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SmokeTest {

    @Autowired
    private lateinit var controller: StatisticsController

    @Test
    @Throws(Exception::class)
    fun contextLoads() {
        assertThat(controller).isNotNull()
    }
}