package com.chelsenok.bots.repositories

import com.chelsenok.bots.entities.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Repository : CrudRepository<Person, Int>