package com.chelsenok.bots

class YouTubeReport {

    lateinit var videoId: String
    var subscriberCount: Long = 0
    var viewCount: Long = 0
    var likeCount: Long = 0
    var dislikeCount: Long = 0
    var commentCount: Long = 0

    constructor (
            videoId: String, subscriberCount: Long, viewCount: Long,
            likeCount: Long, dislikeCount: Long, commentCount: Long
    ) {
        this.videoId = videoId
        this.subscriberCount = subscriberCount
        this.viewCount = viewCount
        this.likeCount = likeCount
        this.dislikeCount = dislikeCount
        this.commentCount = commentCount
    }

    constructor()
}