package com.chelsenok.bots.entities

import javax.persistence.*

@Entity
@Table(name = "stat", schema = "statistics")
class Report {

    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(name = "time")
    var time: Long = 0

    @Column(name = "subscriber_count")
    var subscriberCount: Long = 0

    @Column(name = "view_count")
    var viewCount: Long = 0

    @Column(name = "like_count")
    var likeCount: Long = 0

    @Column(name = "dislike_count")
    var dislikeCount: Long = 0

    @Column(name = "video_id")
    lateinit var videoId: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    lateinit var video: Video

}



