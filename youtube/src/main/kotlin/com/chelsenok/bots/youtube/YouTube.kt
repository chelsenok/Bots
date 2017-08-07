package com.chelsenok.bots.youtube

import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.Video
import java.io.IOException
import java.util.*

class YouTube {

    private val PROPERTIES_FILENAME = "youtube.properties"
    private val PROPERTY_API_KEY = "youtube.apikey"
    private val PART_CHANNELS = "statistics"
    private val PART_VIDEOS = "statistics,snippet"

    private var properties: Properties = Properties()

    private var youtube: YouTube

    init {
        try {
            val file = YouTube::class.java.getResourceAsStream("/" + PROPERTIES_FILENAME)
            properties.load(file)
        } catch (e: IOException) {
        }
        youtube = YouTube.Builder(
                NetHttpTransport(),
                JacksonFactory(),
                HttpRequestInitializer { }
        )
                .setApplicationName("youtube-bots")
                .build()
    }

    fun getReport(id: String): Report? {
        val key = properties.getProperty(PROPERTY_API_KEY)
        val iterator = getVideosIterator(youtube, id, key)
        if (!iterator.hasNext()) {
            return null
        } else {
            val video = iterator.next()
            val channels = youtube.channels().list(PART_CHANNELS)
            channels.key = key
            channels.id = video.snippet.channelId
            val channel = channels.execute().items.iterator().next()
            return Report(
                    channel.statistics.subscriberCount.toLong(),
                    video.statistics.viewCount.toLong(),
                    video.statistics.likeCount.toLong(),
                    video.statistics.dislikeCount.toLong(),
                    video.statistics.commentCount.toLong()
            )
        }
    }

    fun isVideoExists(id: String): Boolean {
        return getVideosIterator(
                youtube,
                id,
                properties.getProperty(PROPERTY_API_KEY)
        )
                .hasNext()
    }

    private fun getVideosIterator(youtube: YouTube, id: String, key: String): MutableIterator<Video> {
        val videos = youtube.videos().list(PART_VIDEOS)
        videos.key = key
        videos.id = id
        return videos.execute().items.iterator()
    }

}
