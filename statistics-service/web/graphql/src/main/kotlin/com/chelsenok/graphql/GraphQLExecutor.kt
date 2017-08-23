package com.chelsenok.graphql

import graphql.ExecutionResult
import graphql.GraphQL
import graphql.GraphQLError
import graphql.language.OperationDefinition
import graphql.parser.Parser

@Suppress("UNCHECKED_CAST")
class GraphQLExecutor {

    companion object {
        fun execute(graphQL: GraphQL, body: Map<*, *>): Map<*, *> {
            val operations = Parser().parseDocument(body["query"] as String).definitions
                    .filterIsInstance<OperationDefinition>()
                    .filter { it.name != null }
                    .map { it.name }

            var result: ExecutionResult
            val variables = body["variables"] as Map<String, Any>?
            val query = body["query"] as String?
            return if (operations.isEmpty() || operations.contains("IntrospectionQuery")) {
                result = if (variables == null) {
                    graphQL.execute(query)
                } else {
                    graphQL.execute(query, null as Any?, variables)
                }
                getHashMap(result.getData(), result.errors)
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
                getHashMap(data, errors)
            }
        }

        private fun getHashMap(data: Any?, errors: Any): Map<*, *> {
            return hashMapOf(
                    "data" to data,
                    "errors" to errors
            )
        }

        fun getExceptionResponse(vararg content: String): Map<*, *> = getHashMap(null, content)
    }

}