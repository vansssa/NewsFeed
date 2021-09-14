package com.example.newsfeed.repository

import com.example.newsfeed.data.HistoryEntity

interface HistoryRepository {
    fun addHistory(history: HistoryEntity)
    fun getHistory(): List<HistoryEntity>
}