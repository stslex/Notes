package com.stslex93.notes.utilites

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    class Failure(val exception: Exception) : Response<Nothing>()
    object Loading : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}