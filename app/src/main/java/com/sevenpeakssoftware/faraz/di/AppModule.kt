package com.sevenpeakssoftware.faraz.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.sevenpeakssoftware.base.NetworkStatusTracker
import com.sevenpeakssoftware.domain.repos.HomeRepository
import com.sevenpeakssoftware.domain.usecases.HomeUseCase
import com.sevenpeakssoftware.faraz.BuildConfig
import com.sevenpeakssoftware.home.vm.HomeViewModel
import com.sevenpeakssoftware.repository.db.CarDatabase
import com.sevenpeakssoftware.repository.db.CarsDao
import com.sevenpeakssoftware.repository.repoImp.HomeRepositoryImp
import com.sevenpeakssoftware.repository.usecaseImp.HomeUseCaseImp
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun featureModules() = listOf(homeModule)

val AppModule = module {
    single {
        NetworkStatusTracker(androidApplication())
    }
}

val LocalModule = module {
    
    fun provideDataBase(application: Application): CarDatabase {
        return Room.databaseBuilder(application, CarDatabase::class.java, "car_database")
                .fallbackToDestructiveMigration().build()
    }
    
    fun provideDao(dataBase: CarDatabase): CarsDao {
        return dataBase.carsDao()
    }
    
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val NetworkModule = module {
    single {
        HttpClient(Android) {
            
            defaultRequest {
                url {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.API_URL
                }
            }
            
            install(Logging) { level = LogLevel.ALL }
            
            install(ContentNegotiation) {
                gson {
                    serializeNulls()
                }
            }
            
            HttpResponseValidator {
                validateResponse {
                    when (it.status.value) {
                        in 300..399 -> {}
                        in 400..499 -> {
                            Log.d("TAG", "${it.status.value}")
                        }
                        in 500..599 -> {}
                    }
                }
                handleResponseExceptionWithRequest { cause: Throwable, request: HttpRequest ->
                    Log.d("TAG", "$cause: $request")
                }
            }
            
            install(ResponseObserver) {
                onResponse { response ->
                    println("HTTP status: ${response.status.value}")
                }
            }
            
        }
    }
}

private val homeModule = module {
    single<HomeRepository> { HomeRepositoryImp(get(), get(), get()) }
    single<HomeUseCase> { HomeUseCaseImp(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}

