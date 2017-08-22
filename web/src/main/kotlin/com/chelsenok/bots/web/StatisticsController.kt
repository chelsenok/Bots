package com.chelsenok.bots.web

import com.chelsenok.graphql.GraphQLExecutor
import graphql.GraphQL
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Suppress("UNCHECKED_CAST")
@RestController
class StatisticsController {

    @Autowired
    private lateinit var graphQL: GraphQL

    @Autowired
    private lateinit var logger: Logger

    @RequestMapping(value = "/bots")
    fun graphql(@RequestBody body: Map<*, *>): Map<*, *> =
            try {
                GraphQLExecutor.execute(graphQL, body)
            } catch (e: ParseCancellationException) {
                GraphQLExecutor.getExceptionResponse(e::class.java.simpleName)
            } catch (e: Exception) {
                logger.error(e)
                GraphQLExecutor.getExceptionResponse("Unknown Exception")
            }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test")
    fun test(): String = "test"
}