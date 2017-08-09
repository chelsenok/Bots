package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.YouTubeReport
import com.chelsenok.bots.dtos.StatInfoGet
import org.springframework.core.convert.converter.Converter

abstract class ConverterFactory {

    companion object {
        fun <T, K> get(obj1: Class<T>, obj2: Class<K>): Converter<T, K>? {
            when (obj1) {
                VideoPost::class.java ->
                    when (obj2) {
                        Video::class.java -> return object : VideoPostToVideoConverter {} as Converter<T, K>
                    }
                Report::class.java ->
                    when (obj2) {
                        StatInfoGet::class.java -> return object : ReportToStatInfoGetConverter {} as Converter<T, K>
                    }
                YouTubeReport::class.java ->
                    when (obj2) {
                        Report::class.java -> return object : YouTubeReportToReportConverter {} as Converter<T, K>
                    }
            }
            return null
        }
    }
}