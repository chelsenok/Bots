package com.chelsenok.bots.web

import com.chelsenok.graphql.GraphQLExecutor
import graphql.GraphQL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Suppress("UNCHECKED_CAST")
@Controller
class StatisticsController {

    @Autowired
    private lateinit var graphQL: GraphQL

    @RequestMapping(value = "/graphql")
    fun graphql(@RequestBody body: Map<*, *>): Map<*, *> = GraphQLExecutor().execute(graphQL, body)
}