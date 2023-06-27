package com.example.findactivity.domain.usecase

import com.example.findactivity.domain.repository.ActRepository

class GetRandomActivityUseCaseImpl(private val actRepository: ActRepository) :
    GetRandomActivityUseCase {

    override suspend operator fun invoke(key: String) = actRepository.getRandomBoredActivity(key)
}