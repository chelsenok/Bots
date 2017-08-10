package com.chelsenok.bots.service.dtos

class StatInfoGet {
    var id: Long = 0
    var time: Long = 0
    var likeCount: Long = 0

    constructor(id: Long, time: Long, likeCount: Long) {
        this.id = id
        this.time = time
        this.likeCount = likeCount
    }

    constructor()
}