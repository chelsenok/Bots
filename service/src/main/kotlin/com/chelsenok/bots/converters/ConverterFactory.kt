package com.chelsenok.bots.converters

import com.chelsenok.bots.dtos.VideoPost
import com.chelsenok.bots.entities.Video

abstract class ConverterFactory {

    companion object {
        inline fun <reified T, reified K> get(): Converter<T, K>? {
            when (T::class) {
                VideoPost::class ->
                    when (K::class) {
                        Video::class -> return VideoResponseWithVideoConverter() as Converter<T, K>
                    }
            }
            return null
        }
    }
}