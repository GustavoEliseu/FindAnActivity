package com.example.findactivity.base

import com.example.findactivity.data.model.MyResult

interface BaseUseCase<T : Any, R: Any> {
    suspend operator fun invoke(param: T): MyResult<R>
}