package com.example.newsfeed.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeed.data.NewsEntity.Companion.TABLE_NEWS


@Entity(tableName = TABLE_NEWS)
data class NewsEntity (@PrimaryKey(autoGenerate = true) var id: Int,
                       @ColumnInfo(name= "newsid") var newsId: String,
                       @ColumnInfo(name= "category") var categoryType: String,
                       @ColumnInfo(name= "title") var title: String?,
                       @ColumnInfo(name= "subtitle") var subtitle: String?,
                       @ColumnInfo(name= "description") var desc: String?,
                       @ColumnInfo(name= "author") var author: String?,
                       @ColumnInfo(name= "date") var date: String) {

    companion object {
        const val TABLE_NEWS = "news"
    }
}