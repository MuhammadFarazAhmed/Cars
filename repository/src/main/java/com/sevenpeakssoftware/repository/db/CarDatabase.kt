package com.sevenpeakssoftware.repository.db

import androidx.room.*
import com.sevenpeakssoftware.domain.model.Car

@Database(entities = [Car::class], version = 1)
@TypeConverters(DataConverter::class,DateConverter::class)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carsDao(): CarsDao
}
