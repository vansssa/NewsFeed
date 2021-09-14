package com.example.newsfeed.repository

import com.example.newsfeed.AppSharePreference
import com.example.newsfeed.api.TopNewsAPIService
import com.example.newsfeed.api.model.TopNewsEntity
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class TopNewsRepositoryImpl @Inject constructor (val apiService: TopNewsAPIService,
                                                 val appSharePreference: AppSharePreference) : TopNewsRepository {

    override fun getTopNews(): Observable<Response<TopNewsEntity?>> {
        val newsList = apiService.getTopNews(appSharePreference.getCategory(), appSharePreference.getCountry())
        return newsList
    }
}