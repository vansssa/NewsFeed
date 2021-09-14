package com.example.newsfeed.api

import com.example.newsfeed.api.model.TopNewsEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap


interface TopNewsAPI {

    @Headers("Accept: application/json")
    @GET("api/v4/top-headlines?")
    open fun getTopNews(@QueryMap getParams: Map<String, String>): Observable<Response<TopNewsEntity?>>

}