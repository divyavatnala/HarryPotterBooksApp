package com.bookslibrary.di

import android.content.Context
import androidx.room.Room
import com.bookslibrary.db.BooksDatabase
import com.bookslibrary.db.BookDao
import com.bookslibrary.data.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Divya V on 19-10-2024.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideItemDataBase(@ApplicationContext context : Context):BooksDatabase{
        return Room.databaseBuilder(context,BooksDatabase::class.java,"mydb").build()
    }

    @Provides
    @Singleton
   fun provideDao(itemdatabase: BooksDatabase):BookDao{
       return itemdatabase.itemDao()
   }

    @Provides
    @Singleton
    fun provideDatabaseRepository(itemDao: BookDao): DatabaseRepository {
        return DatabaseRepository(itemDao)
    }

}