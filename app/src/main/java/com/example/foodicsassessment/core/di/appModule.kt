package com.example.foodicsassessment.core.di

import com.example.foodicsassessment.core.data.local.FoodicsDataBase
import com.example.foodicsassessment.core.data.networking.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val appModule = module {

    // Database singleton instance
    single {
        FoodicsDataBase.getInstance(androidContext())
    }

    // MedicationDao singleton
    single {
        get<FoodicsDataBase>().medicationDao()
    }

    // UserDao singleton
    single {
        get<FoodicsDataBase>().categoryDao()
    }

    // UserDao singleton
    single {
        get<FoodicsDataBase>().cartDao()
    }

    // HttpClient singleton
    single {
        HttpClientFactory.create(CIO.create())
    }
}
