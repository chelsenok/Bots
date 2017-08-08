package com.chelsenok.bots.converters

import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.youtube.YouTubeReport
import org.springframework.core.convert.converter.Converter

class ReportToYouTubeReportConverter : Converter<Report, YouTubeReport> {
    override fun convert(p0: Report): YouTubeReport {
        return YouTubeReport(
                p0.videoId,
                p0.subscriberCount,
                p0.viewCount,
                p0.likeCount,
                p0.dislikeCount,
                p0.commentCount
        )
    }
}