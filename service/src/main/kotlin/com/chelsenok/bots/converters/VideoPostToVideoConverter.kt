package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video
import org.springframework.core.convert.converter.Converter

class VideoPostToVideoConverter : Converter<VideoPost, Video> {
    override fun convert(p0: VideoPost): Video {
        val v: Video = Video()
        v.id = p0.id
        return v
    }

    fun convertEntityToDto(obj: Video): VideoPost {
        val v: VideoPost = VideoPost()
        v.id = obj.id
        return v
    }
}