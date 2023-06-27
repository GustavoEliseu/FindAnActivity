package com.example.findactivity.data.model

import com.example.findactivity.common.GENERAL_DB_ERROR
import com.example.findactivity.common.GENERAL_NETWORK_ERROR
import io.reactivex.Single
import retrofit2.Response
import java.io.IOException

sealed class MyResult<out T : Any>

data class Success<out T : Any>(val data: T) : MyResult<T>()
data class Failure(val httpError: GenericError) : MyResult<Nothing>()


class GenericError(val throwable: Throwable, val errorCode: Int = 0)

inline fun <T : Any> MyResult<T>.onSuccess(action: (T) -> Unit): MyResult<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> MyResult<T>.onFailure(action: (GenericError) -> Unit) {
    if (this is Failure) action(httpError)
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (GenericError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(GenericError(Throwable(message()), code())) }
}

fun <T : Any> Response<T>.getData(): MyResult<T> {
    try {
        onSuccess { return Success(it) }
        onFailure { return Failure(it) }
        return Failure(GenericError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(GenericError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}

fun <T : Any> Single<T>.getDataList(): MyResult<T> {
    try {
        return Success(this.blockingGet())
    } catch (e: IOException) {
        return Failure(GenericError(Throwable(GENERAL_DB_ERROR)))
    }
}

fun<T: List<Any>> Single<T>.getData(): MyResult<Boolean> {
    try {
        return Success(this.blockingGet().size>0)
    } catch (e: IOException) {
        return Failure(GenericError(Throwable(GENERAL_DB_ERROR)))
    }
}