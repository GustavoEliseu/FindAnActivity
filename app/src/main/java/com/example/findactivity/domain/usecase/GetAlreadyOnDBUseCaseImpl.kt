package com.example.findactivity.domain.usecase

import com.example.findactivity.data.model.MyResult
import com.example.findactivity.domain.repository.ActRepository

class GetAlreadyOnDBUseCaseImpl(private val actRepository: ActRepository) :
    GetAlreadyOnDBUseCase {

    override suspend operator fun invoke(key: String): MyResult<Boolean> =
        actRepository.getAlreadyOnDB(key)
}