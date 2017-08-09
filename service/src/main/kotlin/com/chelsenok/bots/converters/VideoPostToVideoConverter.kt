package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video
import org.springframework.core.convert.converter.Converter

interface VideoPostToVideoConverter : Converter<VideoPost, Video> {
    override fun convert(p0: VideoPost): Video {
        val v: Video = Video()
        v.id = p0.id
        return v
    }
}