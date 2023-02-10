package com.sevenpeakssoftware.base

import androidx.compose.animation.core.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.*
import com.sevenpeakssoftware.base.ApiResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}

@Composable
fun <T> rememberFlow(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
                    ): Flow<T> {
    return remember(
            key1 = flow,
            key2 = lifecycleOwner
                   ) { flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED) }
}

@Composable
fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext
                                                   ): State<R> {
    val lifecycleAwareFlow = rememberFlow(flow = this)
    return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
}

@Suppress("StateFlowValueCalledInComposition")
@Composable
fun <T> StateFlow<T>.collectAsStateLifecycleAware(
    context: CoroutineContext = EmptyCoroutineContext
                                                 ): State<T> = collectAsStateLifecycleAware(value, context)

 
fun String.toCustomDate(): String? {
    val date = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH).parse(this)
    
    // current year
    val calendar = Calendar.getInstance()
    val currentYear = calendar[Calendar.YEAR]
    
    if (date != null) {
        calendar.time = date
    }
    //date year
    val dateYear = calendar[Calendar.YEAR]
    
    return if(dateYear < currentYear) date?.let { SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.ENGLISH).format(it) }
    else date?.let { SimpleDateFormat("dd MMMM HH:mm", Locale.ENGLISH).format(it) }
    
}


