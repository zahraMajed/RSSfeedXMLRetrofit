package com.example.rssfeedxmlretrofit


import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

// /api/v1/rss-outbound-feed?_format=xml

class APIclint {
    var retrofitBuilder: Retrofit?= null

    fun getClient(): Retrofit? {
        retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/r/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        return retrofitBuilder
    }
}