package com.example.newsfeed.repository

import android.os.Handler
import android.os.Looper
import com.example.newsfeed.data.HistoryDao
import com.example.newsfeed.data.HistoryEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(private val historyDao: HistoryDao) :
    HistoryRepository {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun addHistory(history: HistoryEntity) {
        executorService.execute {
            historyDao.updateHistory(
                HistoryEntity(
                    history.id,
                    history.newsId,
                    System.currentTimeMillis().toString()
                )
            )
        }
    }

    override fun getHistory(): List<HistoryEntity> {
        TODO("Not yet implemented")
    }
}