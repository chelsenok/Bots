package com.chelsenok.bots

import com.chelsenok.bots.repositories.ReportRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(Application::class))
class Test {

    @Autowired
    private lateinit var repository: ReportRepository

    @Test
    fun createAccount() {
    }
}