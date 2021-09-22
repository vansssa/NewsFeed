package com.example.newsfeed.repository

interface HistoryRepository {
    fun addHistory(history: NewsItems)
    fun getHistory(): List<NewsItems>
}