package com.chelsenok.bots.entities

import javax.persistence.*

@Entity
@Table(name = "stat", schema = "statistics")
class Stat {

    @Id
    @GeneratedValue
    var id: Long = 0

    var time: Long = 0

    var subscribers: Long = 0

    var views: Long = 0

    var likes: Long = 0

    var dislikes: Long = 0

    @Column(name = "video_id")
    lateinit var videoId: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    lateinit var video: Video

}



