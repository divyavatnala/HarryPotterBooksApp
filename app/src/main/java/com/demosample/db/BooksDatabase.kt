package com.demosample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demosample.data.model.BookItem
import com.demosample.data.model.FavBookItem

/**
 * Created by Divya V on 19-10-2024.
 */
@Database(entities = [BookItem::class, FavBookItem::class], version = 3)
abstract class BooksDatabase :RoomDatabase() {
    abstract fun itemDao():BookDao
}