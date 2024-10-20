package com.demosample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demosample.data.model.BookItem
import com.demosample.data.model.FavBookItem
import com.demosample.db.BooksDatabase
import com.demosample.db.BookDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlinx.coroutines.test.runTest
import org.junit.After

/**
 * Created by Divya V on 19-10-2024.
 */
@HiltAndroidTest
class BookDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule= HiltAndroidRule(this)

    @Inject
    lateinit var itemDatabase:BooksDatabase

    @Inject
    lateinit var itemDao: BookDao

    @Before
    fun setUp(){
        hiltAndroidRule.inject()
        itemDao=itemDatabase.itemDao()
    }

    @Test
    fun insertItem_returnSingleItem() = runTest {
        val item= BookItem(0)
        itemDao.insertItem(item)
        val allItems=itemDao.getAllBooks()
        assert(allItems.contains(item))

    }
    @Test
    fun insert_and_retrieve_favorite_books_with_flow() = runBlocking {
        // Create a sample FavoriteBookItem
        val favBook = FavBookItem(1, BookItem(1,"Fav Book Title"), 0L)

        // Insert the favorite book into the database
        itemDao.insertFavBookItem(favBook)

        // Collect the flow of favorite books
        val favBooksFlow = itemDao.getFavBooks()

        // Collect the emitted values from the Flow
        favBooksFlow.collect { favBooks ->
            // Assert that the list contains the favorite book
            assertNotNull(favBooks)
            assertEquals(1, favBooks.size)
            assertEquals("Fav Book Title", favBooks[0].book.title)
        }
    }


    @After
    fun teardown() {
        itemDatabase.close()
    }

}