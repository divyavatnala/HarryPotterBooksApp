package com.demosample.repository

import com.demosample.data.repository.NetworkRepository
import com.demosample.data.api.ApiService
import com.demosample.data.model.BookItem
import com.demosample.utils.NetworkResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response


/**
 * Created by Divya V on 19-10-2024.
 */

@HiltAndroidTest

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class NetworkRepositoryTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Mock
    private lateinit var apiService: ApiService

    @Inject
    lateinit var networkRepository: NetworkRepository

    @Before
    fun setUp() {

        MockitoAnnotations.openMocks(this) // Initialize mocks
        hiltRule.inject() // Inject Hilt dependencies
    }

    @Test
    fun fetch_users_should_return_user_list_from_api() = runTest {
        // Arrange
        val bookItemList = listOf(
            BookItem(id = 1, title = "Book 1","desc"),
            BookItem(id = 2, title = "Book 2","desc")
        )

        `when`(networkRepository.getAllBooks()).thenReturn(bookItemList)

        // Act
        val result = networkRepository.getAllBooks()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Book 1", result[0].title)
        assertEquals("Book 2", result[1].title)

        // Verify the API call
        verify(apiService, times(1)).getAllMovies()
    }

    @Test(expected = Exception::class)
    fun fetchUsers_should_throw_exception_when_api_fails() = runTest {
        // Arrange
        `when`(networkRepository.getAllBooks()).thenThrow(RuntimeException("API call failed"))

        // Act & Assert
        networkRepository.getAllBooks()

        val response = Response.error<List<BookItem>>(404, mock())

        // Mock the API call to return the error response
        `when`(apiService.getHarryPotterBooks()).thenReturn(response)

        // Collect the emitted values from fetchData()
        val results = networkRepository.fetchData().toList()

        // Assert that the first emission is a Loading state
        assertTrue(results[0] is NetworkResult.Loading)

        // Assert that the second emission is an Error state
        assertTrue(results[1] is NetworkResult.Error)
        val errorResult = results[1] as NetworkResult.Error
        assertEquals("Error: 404 - Response.error()", errorResult.message)
    }





}