package com.example.newsfeed.api

import java.util.*
import javax.inject.Inject

class URLParams @Inject constructor() {

    fun topNewsParams(category: String, country: String): Map<String, String> {
        val params: MutableMap<String, String> = HashMap()
        params["token"] = URLs.TOKEN
        params["lang"] = country
        params["country"] = country
        params["topic"] =  category
        return params
    }
}