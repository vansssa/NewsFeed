package com.example.newsfeed.di

import com.example.newsfeed.repository.TopNewsRepository
import com.example.newsfeed.repository.TopNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class TopNewsDataModule {

    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(impl: TopNewsRepositoryImpl): TopNewsRepository
}