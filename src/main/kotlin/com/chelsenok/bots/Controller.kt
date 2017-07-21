package com.chelsenok.bots

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun hello(): Dto {
        return Dto("cheers")
    }

    class Dto(val desc: String)
}