package com.demosample.di

import android.content.Context
import androidx.room.Room
import com.demosample.db.BooksDatabase
import dagger.Module
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

/**
 * Created by Divya V on 19-10-2024.
 */
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
@Module
class TestDatabaseModule {

    fun provideTestDatabse(@ApplicationContext context: Context):BooksDatabase{
        return Room.databaseBuilder(context, BooksDatabase::class.java,"mydb").build()
    }
}