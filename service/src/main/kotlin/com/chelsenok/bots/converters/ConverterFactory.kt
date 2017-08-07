package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import com.chelsenok.bots.youtube.YouTubeReport

abstract class ConverterFactory {

    companion object {
        inline fun <reified T, reified K> get(): Converter<T, K>? {
            when (T::class) {
                VideoPost::class ->
                    when (K::class) {
                        Video::class -> return VideoResponseWithVideoConverter() as Converter<T, K>
                    }
                YouTubeReport::class ->
                    when (K::class) {
                        Report::class -> return YouTubeReportWithReportConverter() as Converter<T, K>
                    }
            }
            return null
        }
    }
}