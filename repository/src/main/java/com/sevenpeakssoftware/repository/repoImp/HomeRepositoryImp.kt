package com.sevenpeakssoftware.repository.repoImp


import androidx.room.withTransaction
import com.sevenpeakssoftware.base.*
import com.sevenpeakssoftware.domain.model.*
import com.sevenpeakssoftware.domain.repos.HomeRepository
import com.sevenpeakssoftware.repository.db.CarDatabase
import com.sevenpeakssoftware.repository.db.CarsDao
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


class HomeRepositoryImp(private val client: HttpClient,
                        private val db: CarDatabase,
                        private val carsDao: CarsDao) : HomeRepository {
    
    override suspend fun fetchHome(): Flow<ApiResult<List<Car>>> = networkBoundResource(query = {
        carsDao.getAllCars()
    }, fetch = {
        try {
            client.post {
                this.url { path("/carlist") }
            }.body<HomeResponse>().content.map { content ->
                content.toCar()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }, saveFetchResult = { CarList ->
        if(CarList.isNotEmpty()) {
            db.withTransaction {
                carsDao.deleteAllCars()
                carsDao.insertCars(CarList)
            }
        }
    })
    
}
