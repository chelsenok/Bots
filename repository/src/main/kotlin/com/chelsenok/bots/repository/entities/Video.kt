package com.chelsenok.bots.repository.entities

import javax.persistence.*

@Entity
@Table(name = "video", schema = "statistics")
class Video {

    @Id
    lateinit var id: String

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "video")
    lateinit var reports: List<Report>
}



