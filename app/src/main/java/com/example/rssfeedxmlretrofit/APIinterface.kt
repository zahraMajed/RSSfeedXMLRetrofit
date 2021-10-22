package com.example.rssfeedxmlretrofit

import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {

    @get:GET("cats/.rss")
    val feed: Call<Feed?>?

    companion object {
        const val BASE_URL = "https://www.reddit.com/r/"
    }

}