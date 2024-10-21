package com.bookslibrary.ui.fragments.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookslibrary.data.model.BookItem
import com.bookslibrary.data.model.FavBookItem
import com.bookslibrary.data.repository.DatabaseRepository
import com.bookslibrary.data.repository.NetworkRepository
import com.bookslibrary.utils.AppUtils
import com.bookslibrary.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: NetworkRepository,
                                             private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _dataState =
        MutableStateFlow<NetworkResult<List<BookItem>>>(NetworkResult.Loading())
    val dataState: StateFlow<NetworkResult<List<BookItem>>> = _dataState


    fun insertFavItem(item: BookItem) {
        viewModelScope.launch {
            insertFavBook(item)
        }
    }

    fun getAllFavItemsInitial() {
        viewModelScope.launch {
            /*When app is installed for the firsttime, to show atleast fav one book
            * adding the first book to favourites */
            databaseRepository.getFavBooks().collect { result ->
                if (result.isEmpty()) {
                    var bookItem = _dataState.value.data?.get(0)
                    bookItem?.let { insertFavBook(it) }
                }
            }
        }
    }

    private suspend fun insertFavBook(item: BookItem) {
        Log.e("insertItemToDB", "  insertItemToDB ")
        databaseRepository.insertFavBookItem(FavBookItem(AppUtils.generateUniqueId(), item, System.currentTimeMillis()))

    }

    fun getNetworkBooks() {
        Log.e("getNetworkBooks", "  getNetworkBooks ")

        viewModelScope.launch {
            repository.fetchData()
                .collect { result ->
                    if (result is NetworkResult.Success) {
                        _dataState.value = result
                        result.data?.let { saveBooksToDatabase(it) }
                    } else if (result is NetworkResult.Error) {
                        val cachedBooks = getAllBooksfromDatabase()
                        if (cachedBooks.isNotEmpty()) {
                            // Update the UI with the cached data
                            _dataState.value = NetworkResult.Success(cachedBooks)
                        } else {
                            // Handle case where no data is available in the database either
                            _dataState.value =
                                NetworkResult.Error("No data available in the database")
                        }
                    }

                }
        }


    }

    private suspend fun saveBooksToDatabase(books: List<BookItem>) {
        withContext(Dispatchers.IO) { // Ensure database operation is done on IO thread
            databaseRepository.insertAllBooks(books) // Assuming insertBooks is a suspend function that inserts a list of books
        }
    }

    suspend fun getAllBooksfromDatabase(): List<BookItem> {
        var list = listOf<BookItem>()
        withContext(Dispatchers.IO) { // Ensure database operation is done on IO thread
            list=databaseRepository.getAllBooks() // Assuming insertBooks is a suspend function that inserts a list of books
        }
        return list
    }
}






