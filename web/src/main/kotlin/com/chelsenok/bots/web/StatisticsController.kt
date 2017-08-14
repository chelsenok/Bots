package com.chelsenok.bots.web

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost
import graphql.GraphQL
import graphql.Scalars.GraphQLString
import graphql.schema.GraphQLFieldDefinition.newFieldDefinition
import graphql.schema.GraphQLObjectType.newObject
import graphql.schema.GraphQLSchema
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
class StatisticsController {

    @Autowired
    private lateinit var statisticsService: StatisticsService

    @PostMapping(value = "/reports")
    fun postVideo(
            @Valid
            @RequestBody
            video: VideoPost,

            result: BindingResult
    ): ResponseEntity<Unit> {
        if (!result.hasErrors() && statisticsService.isVideoValid(video.id)) {
            statisticsService.addVideo(video)
            return ResponseEntity.ok(null)
        } else {
            return ResponseEntity.badRequest().body(null)
        }
    }

    @GetMapping(value = "/filterIds")
    fun getIdByFilter(
            @RequestParam(required = false) videoId: String?,
            @RequestParam(required = false) likeCount: Long?,
            @RequestParam(required = false) dislikeCount: Long?
    ) = statisticsService.getIdByFilter(videoId, likeCount, dislikeCount)

    @GetMapping(value = "/filterBoolean")
    fun getStatusByFilter(
            @RequestParam(required = false) videoId: String?,
            @RequestParam(required = false) likeCount: Long?
    ) = statisticsService.getStatusByFilter(videoId, likeCount)

    @GetMapping(value = "/reports")
    fun getStats(@RequestParam id: String): ResponseEntity<List<StatInfoGet>> {
        if (statisticsService.isVideoExists(id)) {
            return ResponseEntity(statisticsService.getAllStatsInfoByVideoId(id), HttpStatus.OK)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @RequestMapping(value = "/")
    fun helloWorld(@RequestBody body: String): String {
        val queryType = newObject()
                .name("helloWorldQuery")
                .field(newFieldDefinition()
                        .type(GraphQLString)
                        .name("hello")
                        .staticValue(42))
                .build()

        val schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build()

        val graphQL = GraphQL.newGraphQL(schema).build()

        val result = graphQL.execute(body).getData<Map<String, Any>>()
        val json = JSONObject()
        json.putAll(result)
        return json.toString()
    }
}