package com.bookslibrary.data.repository

import android.util.Log
import com.bookslibrary.data.model.BookItem
import com.bookslibrary.utils.NetworkResult
import com.bookslibrary.data.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Divya V on 15-07-2024.
 */

class NetworkRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getAllBooks():List<BookItem>{
        val result = apiService.getHarryPotterBooks()
        var list :List<BookItem> = listOf()
        Log.e("getAllMovies called","get all movies called")
        if(result.isSuccessful && result.body() != null){
             list= result.body()!!
        }
        return list

    }


    fun fetchData(): Flow<NetworkResult<List<BookItem>>>  = flow {
//        emit(NetworkResult.Loading())
        try {
            // Emit loading state
            // Perform the network request
            val response = apiService.getHarryPotterBooks()
            if (response.isSuccessful) {
                // Emit success state with the data
                emit(NetworkResult.Success(response.body() ?: emptyList()))
            } else {
                // Emit error state with an appropriate error message
                emit(NetworkResult.Error("Error: ${response.code()} - ${response.message()}"))
            }

        } catch (e: IOException) {
            // Handle network errors
            emit(NetworkResult.Error("Network Error: ${e.localizedMessage}", e))

        } /*catch (e: HttpException) {
            // Handle HTTP errors
            emit(NetworkResult.Error("HTTP Error: ${e.localizedMessage}", e))
        } */catch (e: Exception) {
            // Handle unknown or generic errors
            emit(NetworkResult.Error("Unknown Error: ${e.localizedMessage}", e))
        }
    }.flowOn(Dispatchers.IO) // Run on IO thread
}