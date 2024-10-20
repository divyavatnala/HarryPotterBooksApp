package com.demosample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demosample.data.model.BookItem
import com.demosample.data.model.FavBookItem
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavBookItem(data:FavBookItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(data:BookItem)
}