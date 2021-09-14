package com.example.newsfeed.api

import com.example.newsfeed.api.model.TopNewsEntity
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TopNewsAPIServiceImpl @Inject constructor(private val api: TopNewsAPI,
                                                private val urlParams: URLParams) : TopNewsAPIService{
    override fun getTopNews(
        category: String,
        country: String
    ): Observable<Response<TopNewsEntity?>> {
        return api.getTopNews(urlParams.topNewsParams(category, country))
    }

}