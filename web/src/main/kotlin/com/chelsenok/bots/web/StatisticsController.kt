package com.chelsenok.bots.web

import graphql.ExecutionResult
import graphql.GraphQL
import graphql.GraphQLError
import graphql.language.OperationDefinition
import graphql.parser.Parser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Suppress("UNCHECKED_CAST")
@RestController
class StatisticsController {

    @Autowired
    private lateinit var graphQL: GraphQL

    @RequestMapping(value = "/graphql")
    fun graphql(@RequestBody body: Map<*, *>): Map<*, *> {
        val operations = Parser().parseDocument(body["query"] as String).definitions
                .filterIsInstance<OperationDefinition>()
                .filter { it.name != null }
                .map { it.name }

        var result: ExecutionResult
        val variables = body["variables"] as Map<String, Any>?
        val query = body["query"] as String?
        if (operations.isEmpty() || operations.contains("IntrospectionQuery")) {
            result = if (variables == null) {
                graphQL.execute(query)
            } else {
                graphQL.execute(query, null as Any?, variables)
            }
            return hashMapOf(
                    "data" to result.getData(),
                    "errors" to result.errors
            )
        } else {
            val data = HashMap<String, Map<*, *>>()
            val errors = HashMap<String, List<GraphQLError>>()
            if (variables == null) {
                operations.forEach { operation: String ->
                    result = graphQL.execute(query, operation, null as Any?)
                    data.put(operation, result.getData<Map<*, *>>())
                    errors.put(operation, result.errors)
                }
            } else {
                operations.forEach { operation: String ->
                    result = graphQL.execute(query, operation, null as Any?, variables)
                    data.put(operation, result.getData<Map<*, *>>())
                    errors.put(operation, result.errors)
                }
            }
            return hashMapOf(
                    "data" to data,
                    "errors" to errors
            )
        }
    }
}