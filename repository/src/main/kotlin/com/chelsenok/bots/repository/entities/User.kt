package com.chelsenok.bots.repository.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user", schema = "statistics")
class User: Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    var id: Long = 0

    @Column(name = "login")
    lateinit var login: String

    @Column(name = "password")
    lateinit var password: String

    @Column(name = "role")
    lateinit var role: String

}




