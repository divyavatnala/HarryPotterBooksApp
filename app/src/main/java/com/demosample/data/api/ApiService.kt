package com.demosample.data.api


import com.demosample.data.model.BookItem
import com.demosample.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Divya V on 16-07-2024.
 */
interface ApiService {
    @GET("/service/api/v1/section/data?path=movies&code=language%2Ccomedy-movies%2Cpopular-movies-kids%2C&offset=-1&count=10&filter=")
    suspend fun getAllMovies() : Response<List<BookItem>>

    @GET(" https://potterapi-fedeperin.vercel.app/en/books")
    suspend fun getHarryPotterBooks() : Response<List<BookItem>>

    suspend fun fetchBooks(): Flow<NetworkResult<List<BookItem>>>


}
