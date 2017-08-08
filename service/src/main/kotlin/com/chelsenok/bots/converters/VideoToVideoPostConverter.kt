package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video
import org.springframework.core.convert.converter.Converter

class VideoToVideoPostConverter : Converter<Video, VideoPost> {

    override fun convert(p0: Video): VideoPost {
        val v: VideoPost = VideoPost()
        v.id = p0.id
        return v
    }

}