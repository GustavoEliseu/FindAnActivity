package com.example.findactivity.domain.usecase

import com.example.findactivity.base.BaseUseCase
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.MyResult

interface SaveOnDBUseCase : BaseUseCase<ActEntity, Boolean> {
    override suspend operator fun invoke(param: ActEntity): MyResult<Boolean>
}