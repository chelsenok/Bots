package com.chelsenok.bots.converters

interface Converterr<T, K> {
    fun toDto(e: K): T
    fun toEntity(d: T): K
}