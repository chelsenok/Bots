package com.chelsenok.bots.converters

import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.YouTubeReport
import com.chelsenok.bots.dtos.StatInfoGet
import org.springframework.core.convert.converter.Converter

interface ReportToStatInfoGetConverter : Converter<Report, StatInfoGet> {
    override fun convert(p0: Report): StatInfoGet {
        return StatInfoGet(
                p0.id,
                p0.time,
                p0.likeCount
        )
    }
}