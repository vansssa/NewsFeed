package com.example.newsfeed.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.newsfeed.data.HistoryEntity.Companion.TABLE_HISTORY

@Dao
interface HistoryDao {

    @Query("Select * from $TABLE_HISTORY")
    fun getHistoryList() : List<HistoryEntity>
    //Note: can't be use ArrayList: https://stackoverflow.com/questions/49259670/not-sure-how-to-convert-cursor-to-this-method-return-type

    @Insert(onConflict = REPLACE)
    fun updateHistory(history: HistoryEntity)
}
