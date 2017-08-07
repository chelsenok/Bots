package com.chelsenok.bots.converters

interface Converter<T, K> {
    fun convertDtoToEntity(obj: T): K
    fun convertEntityToDto(obj: K): T
}