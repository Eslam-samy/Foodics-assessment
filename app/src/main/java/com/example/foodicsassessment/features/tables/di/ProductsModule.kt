package com.example.foodicsassessment.features.tables.di

import com.example.foodicsassessment.features.tables.data.repository.MenuRepositoryImpl
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository
import com.example.foodicsassessment.features.tables.domain.usecase.AddProductToCartUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.ClearCartUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.GetCartUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.GetCategoriesUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.SearchProductsUseCase
import com.example.foodicsassessment.features.tables.presentation.viewModel.ProductsViewModel
import com.example.foodicsassessment.features.tables.presentation.viewModel.TablesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productsModule = module {
    factory<MenuRepository> {
        MenuRepositoryImpl(get(), get(), get(), get())
    } bind MenuRepository::class

    factory {
        GetCategoriesUseCase(get())
    }
    factory {
        GetCartUseCase(get())
    }

    factory {
        AddProductToCartUseCase(get())
    }

    factory {
        ClearCartUseCase(get())
    }


    factory {
        SearchProductsUseCase(get())
    }

    viewModelOf(::TablesViewModel)
    viewModelOf(::ProductsViewModel)


}
