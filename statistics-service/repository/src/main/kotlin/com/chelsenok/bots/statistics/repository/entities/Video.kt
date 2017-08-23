package com.chelsenok.bots.statistics.repository.entities

import javax.persistence.*


@Entity
@Table(name = "video", schema = "public")
class Video {

    @Id
    @Column(name = "id", nullable = false, unique = true, length = 15)
    lateinit var id: String

    @OneToMany(mappedBy = "video", cascade = [(CascadeType.MERGE), (CascadeType.REMOVE)],
            fetch = FetchType.EAGER)
    lateinit var reports: List<Report>
}