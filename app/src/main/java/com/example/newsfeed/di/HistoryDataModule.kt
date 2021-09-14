package com.example.newsfeed.di

import com.example.newsfeed.repository.HistoryRepository
import com.example.newsfeed.repository.HistoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class HistoryDataModule {

    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(impl: HistoryRepositoryImpl): HistoryRepository
}