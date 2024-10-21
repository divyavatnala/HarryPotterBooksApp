package com.bookslibrary.ui.fragments.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookslibrary.data.model.FavBookItem
import com.bookslibrary.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) : ViewModel() {

    // Expose the data as StateFlow
    private val _bookList = MutableStateFlow<List<FavBookItem>>(emptyList<FavBookItem>())
    val bookList: StateFlow<List<FavBookItem>> = _bookList


    fun getFavBooks(){
        viewModelScope.launch {
            databaseRepository.getFavBooks()
                .collect { books ->
                    _bookList.value = books  // Emit the new list of books
                }
        }
    }

//   private suspend fun insertItemToDB(){
//        databaseRepository.insertItem(Item(1,"Divya","here is the data"))
//
//    }
//    private suspend fun getFavBooksFromDB(){
//       var list= databaseRepository.getFavBooks()
//        Log.e("getFavBooksFromDB",list.toString())
//
//    }
}