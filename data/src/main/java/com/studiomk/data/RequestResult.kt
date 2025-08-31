package com.studiomk.data

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T) : RequestResult<T>()
    data class Error(val error: String) : RequestResult<Nothing>()
}