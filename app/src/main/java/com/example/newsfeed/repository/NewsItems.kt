package com.example.newsfeed.repository

import com.example.newsfeed.api.model.TopNewsEntity

data class NewsItems(val id:String,
                     val title:String,
                     val subtitle:String,
                     val description: String,
                     val author: String,
                     val date: String,
                     val url: String,
                     val fromName: String)