package com.example.newsfeed.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.newsfeed.data.HistoryEntity.Companion.TABLE_HISTORY
import com.example.newsfeed.repository.NewsItems

@Dao
interface HistoryDao {

    @Query("Select * from $TABLE_HISTORY order by date DESC")
    fun getHistoryList() : List<HistoryEntity>
    //Note: can't be use ArrayList: https://stackoverflow.com/questions/49259670/not-sure-how-to-convert-cursor-to-this-method-return-type

    @Insert(onConflict = REPLACE)
    fun updateHistory(history: HistoryEntity)

    @Query("Select * from $TABLE_HISTORY where newsid is :newsId")
    fun getHistory(newsId: String) : HistoryEntity?
}
