package com.sevenpeakssoftware.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Data Class to store the data
@Entity(tableName = "cars")
data class Car(
    @PrimaryKey val id: Int = 0,
    val title: String = "",
    val dateTime: String = "",
    val content: List<HomeResponse.Content.Content> = listOf(),
    val ingress: String = "",
    val image: String = "",
    val created: Date = Date(),
    val changed: Date = Date()
)