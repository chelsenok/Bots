package com.chelsenok.bots.service.dtos

class StatInfoGet {
    var id: String = ""
    var time: Long = 0
    var likeCount: Long = 0
    var dislikeCount: Long = 0
    var commentCount: Long = 0
    var viewCount: Long = 0
    var subscriberCount: Long = 0

    constructor(
            id: String, time: Long,
            likeCount: Long,
            dislikeCount: Long,
            commentCount: Long,
            viewCount: Long,
            subscriberCount: Long) {
        this.id = id
        this.time = time
        this.likeCount = likeCount
        this.dislikeCount = dislikeCount
        this.commentCount = commentCount
        this.viewCount = viewCount
        this.subscriberCount = subscriberCount
    }

    constructor()
}