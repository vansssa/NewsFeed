package com.example.newsfeed.api.model

import com.google.gson.annotations.SerializedName

data class TopNewsEntity(
    @field:SerializedName("articles") val articles: List<ArticleBean>,
    @field:SerializedName("totalArticles") val total: Int
) {

    data class ArticleBean(
        @field:SerializedName("title") val title: String,
        @field:SerializedName("description") val description: String,
        @field:SerializedName("content") val content: String,
        @field:SerializedName("url") val url: String,
        @field:SerializedName("image") val image: String,
        @field:SerializedName("publishedAt") val published: String,
        @field:SerializedName("source") val source: SourceBean,
    ) {

        data class SourceBean(
            @field:SerializedName("name") val fromName: String,
            @field:SerializedName("ulr") val fromSite: String
        )
    }

}
