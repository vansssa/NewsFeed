package com.example.newsfeed.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeed.data.HistoryEntity.Companion.TABLE_HISTORY

@Entity(tableName = TABLE_HISTORY)
data class HistoryEntity (@PrimaryKey(autoGenerate = true) var id: Int,
                          @ColumnInfo(name= "newsid") var newsId: String,
                          @ColumnInfo(name= "date") var date: String?) {

    companion object {
        const val TABLE_HISTORY = "history"
    }
}