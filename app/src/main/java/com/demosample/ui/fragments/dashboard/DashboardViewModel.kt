package com.demosample.ui.fragments.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demosample.data.model.BookItem
import com.demosample.data.model.FavBookItem
import com.demosample.data.repository.DatabaseRepository
import com.demosample.data.repository.NetworkRepository
import com.demosample.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: NetworkRepository,
                                             private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _dataState = MutableStateFlow<NetworkResult<List<BookItem>>>(NetworkResult.Loading())
    val dataState: StateFlow<NetworkResult<List<BookItem>>> = _dataState

    fun insertItem(item: BookItem){
        viewModelScope.launch {
            insertFavItem(item)
        }
    }
    fun insertFavItem(item: BookItem){
        viewModelScope.launch {
            insertFavBook(item)
        }
    }
    fun getAllFavItemsInitial(){
        viewModelScope.launch {
            /*When app is installed for the firsttime, to show atleast fav one book
            * adding the first book to favourites */
            databaseRepository.getFavBooks().collect{
                    result ->
                    if(result.isEmpty()){
                        var bookItem=_dataState.value.data?.get(0)
                        bookItem?.let { insertFavBook(it) }
                    }
            }
        }
    }
    private suspend fun insertFavBook(item:BookItem){
        Log.e("insertItemToDB","  insertItemToDB ")
        databaseRepository.insertFavBookItem(FavBookItem(1,item,System.currentTimeMillis()))

    }
    fun getNetworkBooks(){
        Log.e("getNetworkBooks","  getNetworkBooks ")

        viewModelScope.launch {
            repository.fetchData()
                .collect { result ->
                    _dataState.value = result
                }
        }


    }


}