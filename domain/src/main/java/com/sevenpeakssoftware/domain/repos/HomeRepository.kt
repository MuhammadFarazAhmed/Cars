package com.sevenpeakssoftware.domain.repos


import com.sevenpeakssoftware.base.ApiResult
import com.sevenpeakssoftware.domain.model.Car
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    
    suspend fun fetchHome() : Flow<ApiResult<List<Car>>>
    
}