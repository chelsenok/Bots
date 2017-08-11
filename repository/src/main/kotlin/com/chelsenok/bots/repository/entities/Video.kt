package com.chelsenok.bots.repository.entities

import javax.persistence.*

@Entity
@Table(name = "video")
class Video {

    @Id
    @Column(name = "id", length = 15)
    lateinit var id: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
    lateinit var reports: List<Report>
}



