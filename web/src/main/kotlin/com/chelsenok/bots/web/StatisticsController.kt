package com.chelsenok.bots.web

import com.chelsenok.bots.service.StatisticsService
import com.chelsenok.bots.service.dtos.StatInfoGet
import com.chelsenok.bots.service.dtos.VideoPost
import graphql.GraphQL
import graphql.Scalars.GraphQLInt
import graphql.Scalars.GraphQLString
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLArgument
import graphql.schema.GraphQLFieldDefinition.newFieldDefinition
import graphql.schema.GraphQLObjectType.newObject
import graphql.schema.GraphQLSchema
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

    @GetMapping(value = "/reports")
    fun getStats(@RequestParam id: String): ResponseEntity<List<StatInfoGet>> {
        if (statisticsService.isVideoExists(id)) {
            return ResponseEntity(statisticsService.getAllStatsInfoByVideoId(id), HttpStatus.OK)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @GetMapping(value = "/")
    fun getStatus(@RequestParam videoId: String, @RequestParam likeCount: Long, @RequestParam commentCount: Long) = statisticsService.getStatusByFilter(videoId, likeCount, commentCount)

//    GRAPHQL

    @RequestMapping(value = "/graphql")
    fun testGql(@RequestBody body: Map<*, *>): Map<*, *> {
        val query = newObject()
                .name("Query")
                .field(newFieldDefinition()
                        .name("sum")
                        .type(GraphQLInt)
                        .argument(GraphQLArgument.newArgument()
                                .name("arg1")
                                .type(GraphQLInt)
                        )
                        .argument(GraphQLArgument.newArgument()
                                .name("arg2")
                                .type(GraphQLInt)
                        )
                        .dataFetcher(D())
                )
                .field(newFieldDefinition()
                        .name("ok")
                        .type(GraphQLString)
                        .argument(GraphQLArgument.newArgument()
                                .name("arg1")
                                .type(GraphQLString)
                        )
                        .argument(GraphQLArgument.newArgument()
                                .name("arg2")
                                .type(GraphQLString)
                        )
                        .staticValue("this is OK")
                )
                .field(newFieldDefinition()
                        .type(GraphQLString)
                        .name("hello")
                        .staticValue("world")
                )
                .build()

        val schema = GraphQLSchema.newSchema()
                .query(query)
                .build()

        val graphQL = GraphQL.newGraphQL(schema).build()

        val result = graphQL
                .execute(body["query"] as String, null as Any?, body["variables"] as Map<String, Any>)
                .getData<Map<*, *>>()
        return hashMapOf("data" to result)
    }

    class D : DataFetcher<Int> {
        override fun get(p0: DataFetchingEnvironment?): Int {
            val arg1 = p0?.getArgument<Int>("arg1")
            val arg2 = p0?.getArgument<Int>("arg2")
            return if (arg1 != null && arg2 != null) arg1 + arg2 else 0
        }
    }

}