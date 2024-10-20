package com.demosample.data.repository

import com.demosample.data.model.BookItem
import com.demosample.data.model.FavBookItem
import com.demosample.db.BookDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Divya V on 19-10-2024.
 */
class DatabaseRepository @Inject constructor(private val itemDao: BookDao) {

    suspend fun insertItem(item: BookItem){
        itemDao.insertItem(item)
    }
    suspend fun insertFavBookItem(item: FavBookItem){
        itemDao.insertFavBookItem(item)
    }
    suspend fun getFavBooks(): Flow<List<FavBookItem>>{
    return itemDao.getFavBooks()
    }
    suspend fun getAllBooks():List<BookItem>{
        return itemDao.getAllBooks()
    }
}