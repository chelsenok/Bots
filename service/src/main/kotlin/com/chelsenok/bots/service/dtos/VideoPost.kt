package com.chelsenok.bots.service.dtos

import javax.validation.constraints.NotNull

class VideoPost {
    @NotNull
    lateinit var id: String
}