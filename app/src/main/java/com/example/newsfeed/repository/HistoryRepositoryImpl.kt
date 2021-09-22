package com.example.newsfeed.repository

import com.example.newsfeed.data.HistoryDao
import com.example.newsfeed.data.HistoryEntity
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val historyDao: HistoryDao) :
    HistoryRepository {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    override fun addHistory(newsItem: NewsItems) {
        executorService.execute {
            val isOldHistory = historyDao.getHistory(newsItem.id)
            var id = 0

            if (isOldHistory != null) {
                id = isOldHistory.id
            }

            val historyEntity = HistoryEntity(
                id,
                newsItem.id,
                newsItem.title,
                newsItem.description,
                newsItem.fromName,
                newsItem.date,
                newsItem.url,
                Date(System.currentTimeMillis()).toString()
            )

            historyDao.updateHistory(
                historyEntity
            )
        }
    }

    override fun getHistory(): List<NewsItems> {
        val result = mutableListOf<NewsItems>()
        executorService.execute {
            historyDao.getHistoryList().forEach {
                result.add(
                    NewsItems(
                        it.newsId, it.title, "",
                        it.description,  it.atDate, it.newsUrl, it.reporter
                    )
                )
            }
        }
        return result
    }

}