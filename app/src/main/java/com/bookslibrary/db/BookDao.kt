package com.bookslibrary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bookslibrary.data.model.BookItem
import com.bookslibrary.data.model.FavBookItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Divya V on 19-10-2024.
 */
@Dao
interface BookDao {
    @Query("SELECT * FROM Books")
     fun getAllBooks():List<BookItem>

    @Query("SELECT * FROM FavoriteBooks")
     fun getFavBooks(): Flow<List<FavBookItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavBookItem(data:FavBookItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(data:BookItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(books: List<BookItem>)

    @Query("DELETE FROM Books")
    fun clearAllInsertedBooks()

    @Query("SELECT * FROM FavoriteBooks WHERE title = :title LIMIT 1")
    suspend fun getBookByTitle(title: String): FavBookItem?
}