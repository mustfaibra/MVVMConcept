package com.mustfaibra.mvvmconcept.sealed

sealed class DataResponse<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Error<T>(message: String) : DataResponse<T>(message = message)
}
