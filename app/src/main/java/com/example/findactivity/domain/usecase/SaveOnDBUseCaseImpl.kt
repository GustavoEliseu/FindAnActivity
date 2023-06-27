package com.example.findactivity.domain.usecase

import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.MyResult
import com.example.findactivity.domain.repository.ActRepository

class SaveOnDBUseCaseImpl(private val actRepository: ActRepository) : SaveOnDBUseCase {
    override suspend operator fun invoke(param: ActEntity): MyResult<Boolean> = actRepository.saveOnDB(param)

}