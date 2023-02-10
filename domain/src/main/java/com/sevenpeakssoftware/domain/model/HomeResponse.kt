package com.sevenpeakssoftware.domain.model

import java.util.*

data class HomeResponse(val status: String = "",
                        val content: List<Content> = listOf(),
                        val serverTime: Int = 0) {
    
    data class Content(val id: Int = 0,
                       val title: String = "",
                       val dateTime: String = "",
                       val tags: List<Any> = listOf(),
                       val content: List<Content> = listOf(),
                       val ingress: String = "",
                       val image: String = "",
                       val created: Long = 0,
                       val changed: Long = 0) {
        
        data class Content(val type: String = "",
                           val subject: String = "",
                           val description: String = "")
    }
    
}


fun HomeResponse.Content.toCar() = Car(id = id,
        title = title,
        dateTime = dateTime,
        content = content,
        ingress = ingress,
        image = image,
        created = Date(created),
        changed = Date(changed))
