package com.sevenpeakssoftware.domain.usecases

import com.sevenpeakssoftware.base.ApiResult
import com.sevenpeakssoftware.domain.model.Car
import com.sevenpeakssoftware.domain.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeUseCase {
    
    suspend fun fetchHome(): Flow<ApiResult<List<Car>>>
}