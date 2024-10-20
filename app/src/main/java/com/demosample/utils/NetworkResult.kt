package com.demosample.utils


/**
 * Created by Divya V on 20-10-2024.
 */
sealed class NetworkResult<out T>(
    val data: T?=null,
    val message : String ? = null,
    val errorCode: Int? =null
) {
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(message: String?,  val cause: Throwable? = null,errorCode: Int?=null,data: T?=null) : NetworkResult<T>(data, message, errorCode)
//    class Loading : NetworkResult<Nothing>()
class Loading : NetworkResult<Nothing>()

}
