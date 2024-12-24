package com.example.foodicsassessment.features.tables.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.foodicsassessment.core.BaseViewModel
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.usecase.GetCartUseCase
import com.example.foodicsassessment.features.tables.domain.usecase.SearchProductsUseCase
import com.example.foodicsassessment.features.tables.presentation.contract.ProductsUiEvents
import com.example.foodicsassessment.features.tables.presentation.contract.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class ProductsViewModel(
    val getProductsUseCase: SearchProductsUseCase,
    val getCartUseCase: GetCartUseCase
) : BaseViewModel<ProductsUiEvents, ProductsUiState>() {

    private val _productsFlow =
        MutableStateFlow<PagingData<Product>>(PagingData.empty())
    val productsList: StateFlow<PagingData<Product>> = _productsFlow

    override fun initialState(): ProductsUiState {
        return ProductsUiState()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        collectCartUpdates()
    }

    private fun initData(
        categoryId: Int? = null,
        searchKey: String? = null
    ) {
        setState {
            copy(
                categoryId = categoryId ?: -1,
                searchKey = if (searchKey.isNullOrEmpty()) null else searchKey
            )
        }
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            // Clear previous results by emitting an empty PagingData
            _productsFlow.emit(PagingData.empty())

            val res = getProductsUseCase(
                word = viewState.searchKey,
                categoryId = viewState.categoryId
            )
                .cachedIn(viewModelScope)
            // Emit the new results, even if it's empty
            _productsFlow.emit(res.first())
        }
    }

    override fun onEvent(event: ProductsUiEvents) {
        when (event) {
            is ProductsUiEvents.AddToCart -> TODO()
            is ProductsUiEvents.SetInitialData -> initData(
                categoryId = event.categoryId,
                searchKey = event.key
            )
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
