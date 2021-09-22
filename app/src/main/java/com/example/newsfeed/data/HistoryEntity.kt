package com.example.newsfeed.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeed.data.HistoryEntity.Companion.TABLE_HISTORY

@Entity(tableName = TABLE_HISTORY)
data class HistoryEntity (@PrimaryKey(autoGenerate = true) var id: Int = 0,
                          @ColumnInfo(name= "newsid") val newsId: String,
                          @ColumnInfo(name= "title") val title: String,
                          @ColumnInfo(name= "description") val description: String,
                          @ColumnInfo(name= "reporter") val reporter: String,
                          @ColumnInfo(name= "atDate") val atDate: String,
                          @ColumnInfo(name= "url") val newsUrl: String,
                          @ColumnInfo(name= "date") val readDate: String) {

    companion object {
        const val TABLE_HISTORY = "history"
    }
}