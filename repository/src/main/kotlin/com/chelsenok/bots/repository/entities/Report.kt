package com.chelsenok.bots.repository.entities

import javax.persistence.*

@Entity
@Table(name = "report", schema = "statistics")
class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "video_id")
    lateinit var videoId: String

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

    @Column(name = "comment_count")
    var commentCount: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", referencedColumnName = "id", insertable = false, updatable = false)
    lateinit var video: Video

}




