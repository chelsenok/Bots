package com.chelsenok.bots.repository.entities

import javax.persistence.*

@Entity
@Table(name = "video", schema = "statistics")
class Video {

    @Id
    @Column(name = "id", length = 15, nullable = false, unique = true)
    lateinit var id: String

    @OneToMany(mappedBy = "video", cascade = arrayOf(CascadeType.MERGE, CascadeType.REMOVE),
            fetch = FetchType.EAGER)
    lateinit var reports: List<Report>
}



