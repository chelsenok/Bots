package com.chelsenok.bots.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.chelsenok.bots.dtos.PersonInfo
import com.chelsenok.bots.services.TestServiceImpl

@RestController
class Controller {

    @Autowired
    lateinit var service: TestServiceImpl

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun hello(): PersonInfo = service.getPersonInfo(1)
}