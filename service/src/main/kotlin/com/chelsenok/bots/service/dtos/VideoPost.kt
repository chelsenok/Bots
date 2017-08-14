package com.chelsenok.bots.service.dtos

import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class VideoPost {
    @NotNull
    @NotEmpty
    @Size(max = 15)
    lateinit var id: String
}