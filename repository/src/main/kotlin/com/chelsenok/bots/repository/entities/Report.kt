package com.chelsenok.bots.repository.entities

import javax.persistence.*

@Entity
@Table(name = "report", schema = "statistics")
class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    var id: Long = 0

    @Column(name = "video_id", nullable = false, unique = false)
    lateinit var videoId: String

    @Column(name = "time", nullable = false, unique = false)
    var time: Long = 0

    @Column(name = "subscriber_count", nullable = false, unique = false)
    var subscriberCount: Long = 0

    @Column(name = "view_count", nullable = false, unique = false)
    var viewCount: Long = 0

    @Column(name = "like_count", nullable = false, unique = false)
    var likeCount: Long = 0

    @Column(name = "dislike_count", nullable = false, unique = false)
    var dislikeCount: Long = 0

    @Column(name = "comment_count", nullable = false, unique = false)
    var commentCount: Long = 0

    @ManyToOne
    @JoinColumn(name = "video_id", referencedColumnName = "id", insertable = false,
            updatable = false)
    lateinit var video: Video

}




