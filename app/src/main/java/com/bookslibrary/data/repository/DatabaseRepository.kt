package com.bookslibrary.data.repository

import com.bookslibrary.data.model.BookItem
import com.bookslibrary.data.model.FavBookItem
import com.bookslibrary.db.BookDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Divya V on 19-10-2024.
 */
class DatabaseRepository @Inject constructor(private val bookDao: BookDao) {

    suspend fun insertItem(item: BookItem){
        bookDao.insertBook(item)
    }
    suspend fun insertAllBooks(books: List<BookItem>) {
        bookDao.clearAllInsertedBooks()
        bookDao.insertAllBooks(books)
    }
    suspend fun insertFavBookItem(item: FavBookItem){
        val existingBook = bookDao.getBookByTitle(item.book.title.toString())

        if (existingBook == null) {
            // If the book doesn't exist, insert it
            bookDao.insertFavBookItem(item)
        }

    }
    suspend fun getFavBooks(): Flow<List<FavBookItem>>{
    return bookDao.getFavBooks()
    }
    suspend fun getAllBooks():List<BookItem>{
        return bookDao.getAllBooks()
    }

}