package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.youtube.YouTubeReport
import org.springframework.core.convert.converter.Converter

abstract class ConverterFactory {

    companion object {
        inline fun <reified T, reified K> get(): Converter<T, K>? {
            when (T::class) {
                VideoPost::class ->
                    when (K::class) {
                        Video::class -> return VideoPostToVideoConverter() as Converter<T, K>
                    }
                Report::class ->
                    when (K::class) {
                        YouTubeReport::class -> return ReportToYouTubeReportConverter() as Converter<T, K>
                    }
                Video::class ->
                    when (K::class) {
                        VideoPost::class -> return VideoToVideoPostConverter() as Converter<T, K>
                    }
                YouTubeReport::class ->
                    when (K::class) {
                        Report::class -> return YouTubeReportToReportConverter() as Converter<T, K>
                    }
            }
            return null
        }
    }
}