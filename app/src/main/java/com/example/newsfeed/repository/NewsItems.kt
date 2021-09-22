package com.example.newsfeed.repository

data class NewsItems(val id:String,
                     val title:String,
                     val subtitle:String,
                     val description: String,
                     val date: String,
                     val url: String,
                     val fromName: String)