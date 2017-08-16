package com.chelsenok.bots.web

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost
import graphql.ExecutionResult
import graphql.GraphQL
import graphql.GraphQLError
import graphql.language.OperationDefinition
import graphql.parser.Parser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@Suppress("UNCHECKED_CAST")
@RestController
class StatisticsController {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    @Autowired
    private lateinit var graphQL: GraphQL

    @PostMapping(value = "/reports")
    fun postVideo(
            @Valid
            @RequestBody
            video: VideoPost,

            result: BindingResult
    ): ResponseEntity<Unit> {
        return if (!result.hasErrors() && statisticsService.isVideoValid(video.id)) {
            statisticsService.addVideo(video)
            ResponseEntity.ok(null)
        } else {
            ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping(value = "/reports")
    fun getStats(@RequestParam id: String): ResponseEntity<List<StatInfoGet>> {
        return if (statisticsService.isVideoExists(id)) {
            ResponseEntity(statisticsService.getAllStatsInfoByVideoId(id), HttpStatus.OK)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

//    TEST

    @GetMapping(value = "/")
    fun getStatus(
            @RequestParam videoId: String,
            @RequestParam likeCount: Long,
            @RequestParam commentCount: Long
    ) = statisticsService.getStatusByFilter(videoId, likeCount, commentCount)

//    TEST

//    GRAPHQL=


    @RequestMapping(value = "/graphql")
    fun graphql(@RequestBody body: Map<*, *>): Map<*, *> {
        val operations = Parser().parseDocument(body["query"] as String).definitions
                .filterIsInstance<OperationDefinition>()
                .filter { it.name != null }
                .map { it.name }

        var result: ExecutionResult
        val variables = body["variables"] as Map<String, Any>?
        val query = body["query"] as String?
        if (operations.isEmpty()) {
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

//    GRAPHQL

}