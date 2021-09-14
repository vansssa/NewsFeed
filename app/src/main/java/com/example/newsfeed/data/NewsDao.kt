package com.example.newsfeed.data

import androidx.room.Dao
import androidx.room.Query
import com.example.newsfeed.data.NewsEntity.Companion.TABLE_NEWS

@Dao
interface NewsDao {

    @Query("Select * from $TABLE_NEWS order by date DESC")
    fun getAllNewsFeed() : List<NewsEntity>
    //Note: can't be use ArrayList: https://stackoverflow.com/questions/49259670/not-sure-how-to-convert-cursor-to-this-method-return-type

    @Query("Select * from $TABLE_NEWS where category IN (:ids) AND newsid IN (:subs) order by date DESC")
    fun getNewsFeedByType(ids: List<String>, subs: List<String>) : List<NewsEntity>
}
