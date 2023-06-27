package com.example.findactivity.domain.usecase

import com.example.findactivity.base.BaseUseCase
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.MyResult


interface GetRandomActivityUseCase : BaseUseCase<String, ActEntity> {

    override suspend operator fun invoke(param: String): MyResult<ActEntity>
}
