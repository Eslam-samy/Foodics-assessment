package com.example.foodicsassessment.features.tables.presentation.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.example.foodicsassessment.core.BaseViewModel
import com.example.foodicsassessment.features.tables.domain.usecase.GetCategoriesUseCase
import com.example.foodicsassessment.features.tables.presentation.contract.TablesUiEvents
import com.example.foodicsassessment.features.tables.presentation.contract.TablesUiState
import kotlinx.coroutines.launch
import com.example.foodicsassessment.core.domain.util.Result
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.usecase.AddProductToCartUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.ClearCartUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.GetCartUseCase

class TablesViewModel(
    val getCategoriesUseCase: GetCategoriesUseCase,
    val addProductToCartUseCase: AddProductToCartUseCase,
    val getCartUseCase: GetCartUseCase,
    val clearCartUseCase: ClearCartUseCase
) : BaseViewModel<TablesUiEvents, TablesUiState>() {

    override fun initialState(): TablesUiState {
        return TablesUiState()
    }

    override fun onEvent(event: TablesUiEvents) {
        when (event) {
            is TablesUiEvents.AddToCart -> {
                // Handle adding product to cart
                addProductToCart(event.product)
            }

            is TablesUiEvents.OnCategorySelected -> {
                setState {
                    copy(
                        selectedTabIndex = event.index
                    )
                }
            }

            is TablesUiEvents.OnError -> TODO()
            is TablesUiEvents.OnSearchKeyChanged -> {
                setState {
                    copy(
                        searchKey = event.key
                    )
                }
            }

            TablesUiEvents.OnCheckoutClicked -> clearCart()
        }

    }

    private fun clearCart() {
        viewModelScope.launch {
            when (val result = clearCartUseCase()) {
                is Result.Success -> {
                    setState {
                        copy(
                            cartCount = 0,
                            cartItems = emptyList()
                        )

                    }
                }

                is Result.Error -> {
                    // Handle error in clearing cart
                }
            }
        }
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        getCategories()
        collectCartUpdates()
    }


    private fun getCategories() {
        viewModelScope.launch {
            setState { copy(loading = true) }
            when (val result = getCategoriesUseCase()) {
                is Result.Error -> {
//                    when (result.error.type) {
//                        NetworkError.UNAUTHORIZED -> {
//                            showUnauthenticatedState(result.error.toUiText())
//                        }
//
//                        else -> showToast(result.error.toUiText())
//                    }
                }

                is Result.Success -> {
                    setState {
                        copy(
                            categories = result.data
                        )
                    }
                }
            }

            setState { copy(loading = false) }
        }
    }

    private fun addProductToCart(product: Product) {
        viewModelScope.launch {
            when (val result = addProductToCartUseCase(product, 1)) {
                is Result.Success -> {
                    // Successfully added product to the cart
                    // No additional actions needed, as cart updates in real-time via Flow
                }

                is Result.Error -> {
                    // Handle error in adding to cart
                }
            }
        }
    }

    private fun collectCartUpdates() {
        viewModelScope.launch {
            getCartUseCase.execute().collect { cartItems ->
                // Update the UI state with the latest cart data
                setState {
                    copy(
                        cartItems = cartItems,
                        cartCount = cartItems.sumOf { it.amount }
                    )
                }
            }
        }
    }
}