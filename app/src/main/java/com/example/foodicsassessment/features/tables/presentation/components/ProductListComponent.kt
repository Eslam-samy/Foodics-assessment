package com.example.foodicsassessment.features.tables.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.foodicsassessment.core.CommonUiEffect
import com.example.foodicsassessment.core.ObserveAsEvents
import com.example.foodicsassessment.core.ObserveLifecycleEvents
import com.example.foodicsassessment.core.presentation.components.MainAppLoader
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.presentation.contract.ProductsUiEvents
import com.example.foodicsassessment.features.tables.presentation.viewModel.ProductsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListComponent(
    modifier: Modifier = Modifier,
    categoryId: Int? = null,
    searchKey: String? = null,
    handleCommonEvents: (CommonUiEffect) -> Unit,
    onProductClicked: (Product) -> Unit = {},

    ) {
    val viewModel: ProductsViewModel = koinViewModel()
    viewModel.ObserveLifecycleEvents(lifecycle = LocalLifecycleOwner.current.lifecycle)


    val lazyPagingItems: LazyPagingItems<Product> =
        viewModel.productsList.collectAsLazyPagingItems()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Calculate the number of columns dynamically
    val columnCount = when {
        screenWidth < 400.dp -> 2
        screenWidth < 600.dp -> 3
        else -> 4
    }



    LaunchedEffect(searchKey) {
        viewModel.onEvent(
            ProductsUiEvents.SetInitialData(
                categoryId = categoryId,
                key = searchKey,
            )
        )
    }

    ObserveAsEvents(flow = viewModel.effectFlow) { event ->
        when (event) {
            is CommonUiEffect -> handleCommonEvents(event)
        }
    }

    if (lazyPagingItems.loadState.refresh is LoadState.Loading) {
        MainAppLoader()
    } else {
        if (lazyPagingItems.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
//                EmptyState(
//                    modifier = Modifier.fillMaxWidth(),
//                    title = stringResource(R.string.no_results_found),
//                    hasButton = false
//                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columnCount), // Set dynamic columns
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    count = lazyPagingItems.itemCount,
                    key = { index -> lazyPagingItems[index]?.id!! },
                    contentType = { index -> lazyPagingItems[index] }
                ) { index ->
                    lazyPagingItems[index]?.let { product ->
                        ProductItemComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)  // Ensure square cells
                                .background(
                                    Color.Transparent,
                                    RoundedCornerShape(20.dp)
                                ),
                            product = product,
                            onItemClick = {
                                onProductClicked(product)
                            },
                            cartCount = viewModel.viewState.cartItems.firstOrNull {
                                it.product.id == product.id
                            }?.amount ?: 0
                        )
                    }
                }
            }
        }
    }
}