package com.sevenpeakssoftware.repository.db

import androidx.room.*
import com.sevenpeakssoftware.domain.model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars ORDER BY created DESC")
    fun getAllCars(): Flow<List<Car>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCars(cars: List<Car>)
    
    @Update
    suspend fun updateCars(cars: List<Car>)

    @Query("DELETE FROM cars")
    suspend fun deleteAllCars()
}