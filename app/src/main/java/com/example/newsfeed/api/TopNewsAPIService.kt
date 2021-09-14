package com.example.newsfeed.api

import com.example.newsfeed.AppSharePreference.Companion.CATEGORY_DEFAULT_VALUE
import com.example.newsfeed.AppSharePreference.Companion.COUNTRY_DEFAULT_VALUE
import com.example.newsfeed.api.model.TopNewsEntity
import io.reactivex.Observable
import retrofit2.Response

interface TopNewsAPIService {
    fun getTopNews(category: String = COUNTRY_DEFAULT_VALUE, country: String = CATEGORY_DEFAULT_VALUE): Observable<Response<TopNewsEntity?>>
}