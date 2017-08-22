package com.chelsenok.bots.application

import com.chelsenok.bots.service.user.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    @Qualifier("dataSource")
    private lateinit var dataSource: DataSource

    @Autowired
    private lateinit var userDetailsService: CustomUserDetailsService

    @Bean
    @Primary
    open fun passwordEncoder(): PasswordEncoder
            = BCryptPasswordEncoder()

    @Bean
    @Primary
    open fun tokenStore(): JdbcTokenStore =
            JdbcTokenStore(dataSource)

    @Bean
    protected open fun authorizationCodeServices(): AuthorizationCodeServices =
            JdbcAuthorizationCodeServices(dataSource)


    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer) {
        oauthServer
                .passwordEncoder(passwordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .approvalStoreDisabled()
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Primary
    open fun tokenServices(): DefaultTokenServices {
        val tokenServices = DefaultTokenServices()
        tokenServices.setSupportRefreshToken(true)
        tokenServices.setTokenStore(tokenStore())
        return tokenServices
    }
}
