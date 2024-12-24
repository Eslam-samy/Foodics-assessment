package com.example.foodicsassessment.core.presentation

import android.app.Application
import com.example.foodicsassessment.core.di.appModule
import com.example.foodicsassessment.features.tables.di.productsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                buildList {
                    addAll(
                        listOf(
                            appModule,
                            productsModule
                        )
                    )
                }

            )
        }
    }
}