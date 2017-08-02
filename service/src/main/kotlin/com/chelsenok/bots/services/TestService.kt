package com.chelsenok.bots.services

import com.chelsenok.bots.dtos.PersonInfo
import org.springframework.stereotype.Service

interface TestService {
    fun getPersonInfo(id: Int): PersonInfo
}