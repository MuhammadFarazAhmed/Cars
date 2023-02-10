package com.sevenpeakssoftware.repository.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sevenpeakssoftware.domain.model.HomeResponse

class DataConverter {
    
    @TypeConverter
    fun fromObject(value: List<HomeResponse.Content.Content>): String {
        val gson = Gson()
        val type = object : TypeToken<List<HomeResponse.Content.Content>>() {}.type
        return gson.toJson(value, type)
    }
    
    @TypeConverter
    fun toList(value: String): List<HomeResponse.Content.Content> {
        val gson = Gson()
        val type = object : TypeToken<List<HomeResponse.Content.Content>>() {}.type
        return gson.fromJson(value, type)
    }
}