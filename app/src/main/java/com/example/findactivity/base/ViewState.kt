package com.example.findactivity.base

sealed class ViewState<out T : Any>
class ViewStateSuccess<out T : Any>(val data: T) : ViewState<T>()
class ViewStateError<out T : Any>(val error: Throwable) : ViewState<T>()
class Loading<out T : Any> : ViewState<T>()
class NoInternetState<T : Any> : ViewState<T>()

class NoIntentState<T : Any> : ViewState<T>()