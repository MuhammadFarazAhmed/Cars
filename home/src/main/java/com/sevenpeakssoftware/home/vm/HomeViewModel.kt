package com.sevenpeakssoftware.home.vm

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.sevenpeakssoftware.base.*
import com.sevenpeakssoftware.base.ApiStatus.*
import com.sevenpeakssoftware.domain.model.Car
import com.sevenpeakssoftware.domain.usecases.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel constructor(application: Application,
                                private val homeUseCase: HomeUseCase,
                                networkStatusTracker: NetworkStatusTracker) :
    AndroidViewModel(application) {
    
    
    /**
     * make these in a separate class called HomeUIState
     */
    var cars: MutableStateFlow<List<Car>> = MutableStateFlow(emptyList())
    var status = mutableStateOf(IDLE)
    val isRefreshing = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    val snack = mutableStateOf(false)
    
    /**
     *
     */
    
    
    val networkStatus = networkStatusTracker.networkStatus.map(onUnavailable = {
        snack.value = true
    }, onAvailable = {
        snack.value = false
        fetchHomeData(homeUseCase)
    }).flowOn(Dispatchers.IO)
    
    
    init {
        fetchHomeData(homeUseCase)
    }
    
    
    private fun fetchHomeData(homeUseCase: HomeUseCase) {
        viewModelScope.launch {
            homeUseCase.fetchHome().collect {
                status.value = it.status
                it.data?.let { content -> cars.value = content }
                errorMessage.value = it.message.toString()
            }
        }
    }
    
    fun refresh() {
        fetchHomeData(homeUseCase)
    }
}