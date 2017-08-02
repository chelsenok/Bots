package com.chelsenok.bots.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "person", schema = "test")
class Person {

    @Id
    @GeneratedValue
    var id: Int = 0

    var name: String = ""
}



