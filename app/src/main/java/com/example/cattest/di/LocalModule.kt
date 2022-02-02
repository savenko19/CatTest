package com.example.cattest.di

import android.app.Application
import androidx.room.Room
import com.example.cattest.data.local.CatsDao
import com.example.cattest.data.local.CatsDatabase
import com.example.cattest.data.repository.LocalRepositoryImpl
import com.example.cattest.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule(
    private val application: Application
) {

    @Singleton
    @Provides
    fun provideDatabase() =
        Room.databaseBuilder(
            application,
            CatsDatabase::class.java,
            CatsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    fun providesDao(database: CatsDatabase) =
        database.catsDao()

    @Provides
    fun provideLocalRepository(dao: CatsDao): LocalRepository =
        LocalRepositoryImpl(dao)
}