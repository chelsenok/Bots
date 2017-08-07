package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video

class VideoResponseWithVideoConverter: Converter<VideoPost, Video> {
    override fun convertDtoToEntity(obj: VideoPost): Video {
        val v: Video = Video()
        v.id = obj.v
        return v
    }

    override fun convertEntityToDto(obj: Video): VideoPost {
        val v: VideoPost = VideoPost()
        v.v = obj.id
        return v
    }
}