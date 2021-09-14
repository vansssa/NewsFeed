package com.example.newsfeed.repository

import com.example.newsfeed.api.model.TopNewsEntity
import io.reactivex.Observable
import retrofit2.Response

interface TopNewsRepository {
    fun getTopNews(): Observable<Response<TopNewsEntity?>>
}