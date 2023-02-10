package com.sevenpeakssoftware.repository.usecaseImp

import com.sevenpeakssoftware.base.ApiResult
import com.sevenpeakssoftware.domain.model.Car
import com.sevenpeakssoftware.domain.repos.HomeRepository
import com.sevenpeakssoftware.domain.usecases.HomeUseCase
import kotlinx.coroutines.flow.Flow


class HomeUseCaseImp  constructor(private val homeRepository: HomeRepository) : HomeUseCase {
    
    override suspend fun fetchHome(): Flow<ApiResult<List<Car>>> = homeRepository.fetchHome()
}

