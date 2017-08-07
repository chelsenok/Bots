package com.chelsenok.bots.converters

import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.youtube.YouTubeReport
import java.util.*

class YouTubeReportWithReportConverter : Converter<YouTubeReport, Report> {
    override fun convertDtoToEntity(obj: YouTubeReport): Report {
        val report = Report()
        report.videoId = obj.videoId
        report.time = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis
        report.subscriberCount = obj.subscriberCount
        report.viewCount = obj.viewCount
        report.likeCount = obj.likeCount
        report.dislikeCount = obj.dislikeCount
        report.commentCount = obj.commentCount
        return report
    }

    override fun convertEntityToDto(obj: Report): YouTubeReport {
        return YouTubeReport(
                obj.videoId,
                obj.subscriberCount,
                obj.viewCount,
                obj.likeCount,
                obj.dislikeCount,
                obj.commentCount
        )
    }
}