@file:Suppress("UNCHECKED_CAST")

package com.chelsenok.bots.converters

import com.chelsenok.bots.YouTubeReport
import com.chelsenok.bots.dtos.StatInfoGet
import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Report
import com.chelsenok.bots.entities.Video
import org.springframework.core.convert.converter.Converter

abstract class ConverterFactory {

    companion object {
        inline fun <reified From, reified To> get(): Converter<From, To>? {
            when (From::class) {
                VideoPost::class ->
                    when (To::class) {
                        Video::class ->
                            return object : VideoPostToVideoConverter {} as Converter<From, To>
                    }
                Report::class ->
                    when (To::class) {
                        StatInfoGet::class ->
                            return object : ReportToStatInfoGetConverter {} as Converter<From, To>
                    }
                YouTubeReport::class ->
                    when (To::class) {
                        Report::class ->
                            return object : YouTubeReportToReportConverter {} as Converter<From, To>
                    }
            }
            return null
        }
    }
}