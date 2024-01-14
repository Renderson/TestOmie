package com.renderson.testomie.di

import android.content.Context
import androidx.room.Room
import com.renderson.testomie.data.AppDatabase
import com.renderson.testomie.data.OmieDao
import com.renderson.testomie.data.OmieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OmieModule{

    @Provides
    @Singleton
    fun providerAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "room_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providerDao(appDatabase: AppDatabase): OmieDao {
        return appDatabase.omieDao()
    }

    @Provides
    @Singleton
    fun providerTaskRepository(omieDao: OmieDao): OmieRepository {
        return OmieRepository(omieDao)
    }
}