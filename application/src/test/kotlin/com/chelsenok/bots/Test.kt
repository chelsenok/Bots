package com.chelsenok.bots

import com.chelsenok.bots.entities.Report
import org.junit.Test
import org.junit.runner.RunWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import kotlin.test.assertNotNull

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(Application::class))
class Test {

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Test
    fun createAccount() {
        val y = YouTubeReport(
                "lala", 0, 0, 0, 0, 0
        )
        val e = modelMapper.map(y, Report::class.java)
        assertNotNull(e)
    }
}