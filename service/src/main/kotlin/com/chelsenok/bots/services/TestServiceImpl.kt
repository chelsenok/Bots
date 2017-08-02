package com.chelsenok.bots.services

import com.chelsenok.bots.dtos.PersonInfo
import com.chelsenok.bots.repositories.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestServiceImpl: TestService {

    @Autowired
    lateinit var repository: Repository

    override fun getPersonInfo(id: Int): PersonInfo =
            PersonInfo(repository.findOne(id).name)

}
