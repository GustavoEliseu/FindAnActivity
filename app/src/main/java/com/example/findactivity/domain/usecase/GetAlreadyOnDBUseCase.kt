package com.example.findactivity.domain.usecase

import com.example.findactivity.base.BaseUseCase
import com.example.findactivity.data.model.MyResult

interface GetAlreadyOnDBUseCase : BaseUseCase<String, Boolean> {
    override suspend operator fun invoke(param: String): MyResult<Boolean>
}