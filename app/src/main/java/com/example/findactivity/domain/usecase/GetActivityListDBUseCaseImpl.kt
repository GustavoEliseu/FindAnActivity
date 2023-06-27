package com.example.findactivity.domain.usecase

import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.MyResult
import com.example.findactivity.domain.repository.ActRepository

class GetActivityListDBUseCaseImpl (private val actRepository: ActRepository) :
    GetActivityListDBUseCase {
    override suspend operator fun invoke(param: String): MyResult<List<ActEntity>> = actRepository.getActivityList()
}