package com.chelsenok.bots.statistics.service.dtos

class VideoGet {
    lateinit var reports: List<StatInfoGet>

    constructor(
            reports: List<StatInfoGet>
    ) {
        this.reports = reports
    }

    constructor()
}