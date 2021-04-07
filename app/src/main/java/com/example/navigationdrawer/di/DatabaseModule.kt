package com.example.navigationdrawer.di

import android.content.Context
import androidx.room.Room
import com.example.navigationdrawer.db.UserDao
import com.example.navigationdrawer.db.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): UserDataBase {
        return Room.databaseBuilder(
            appContext,
            UserDataBase::class.java,
            "user.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLogDao(database: UserDataBase): UserDao {
        return database.userDao()
    }
}