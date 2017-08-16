package com.chelsenok.bots.service.datafetchers

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

class AddVideoDataFetcher : DataFetcher<String> {
    override fun get(p0: DataFetchingEnvironment?): String {
        val id = p0?.getArgument<String>("id")
        return "i'm here with" + id
    }
}