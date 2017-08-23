package com.chelsenok.bots.auth.service.utils

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class SecurityUtilsImpl : SecurityUtils {
    override fun getCurrentUserLogin(): String? {
        val securityContext = SecurityContextHolder.getContext()
        val authentication = securityContext.authentication
        var userName: String? = null
        if (authentication != null) {
            if (authentication.principal is UserDetails) {
                val springSecurityUser = authentication.principal as UserDetails
                userName = springSecurityUser.username
            } else if (authentication.principal is String) {
                userName = authentication.principal as String
            }
        }
        return userName
    }

}