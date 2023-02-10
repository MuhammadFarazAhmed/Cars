package com.sevenpeakssoftware.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sevenpeakssoftware.base.ApiStatus.*
import com.sevenpeakssoftware.base.collectAsStateLifecycleAware
import com.sevenpeakssoftware.base.theme.White
import com.sevenpeakssoftware.domain.model.Car
import com.sevenpeakssoftware.home.vm.HomeViewModel
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(vm: HomeViewModel = getViewModel()) {
    
    vm.networkStatus.collectAsStateLifecycleAware(false)
    val pullRefreshState = rememberPullRefreshState(vm.isRefreshing.value, { vm.refresh() })
   
    val cars = vm.cars.collectAsStateLifecycleAware()
    
    val (snackBarVisibleState, _) = remember { vm.snack }
    
    when (vm.status.value) {
        IDLE -> {}
        SUCCESS -> {
            vm.isRefreshing.value = false
            SuccessComposable(cars.value, pullRefreshState)
        }
        ERROR -> {
            vm.isRefreshing.value = false
            ErrorComposable(errorMessage = vm.errorMessage.value)
        }
        LOADING -> {
            vm.isRefreshing.value = true
            LoadingComposable()
        }
    }
    
    if (snackBarVisibleState || cars.value.isEmpty()) {
        Snackbar(backgroundColor = Color.Red,
                modifier = Modifier.padding(8.dp)
                ) { Text(text = "Internet is not connected",color = White) }
    }
    
}

@Composable
fun LoadingComposable() {
    Box(contentAlignment = Center, modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.size(52.dp), color = Color.White, 5.dp)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SuccessComposable(cars: List<Car>, pullRefreshState: PullRefreshState) {
    Box(contentAlignment = Center,
            modifier = Modifier
                    .pullRefresh(pullRefreshState)
                    .background(Color.Black)
                    .fillMaxSize()) {
        LazyColumn {
            items(cars) { car ->
                Car(car)
            }
        }
    }
}

@Composable
fun ErrorComposable(errorMessage: String) {
    Box(contentAlignment = Center, modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()) {
        Text(text = errorMessage, color = Color.White, textAlign = TextAlign.Center)
    }
}

