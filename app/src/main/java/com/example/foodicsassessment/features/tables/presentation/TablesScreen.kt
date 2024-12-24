package com.example.foodicsassessment.features.tables.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.foodicsassessment.R
import com.example.foodicsassessment.core.CommonUiEffect
import com.example.foodicsassessment.core.ObserveAsEvents
import com.example.foodicsassessment.core.ObserveLifecycleEvents
import com.example.foodicsassessment.core.presentation.components.CommonTextField
import com.example.foodicsassessment.core.presentation.components.MainAppLoader
import com.example.foodicsassessment.core.presentation.ui.theme.colors
import com.example.foodicsassessment.core.presentation.ui.theme.small_text
import com.example.foodicsassessment.core.presentation.ui.theme.spacing
import com.example.foodicsassessment.features.tables.domain.model.common.Category
import com.example.foodicsassessment.features.tables.presentation.components.ProductListComponent
import com.example.foodicsassessment.features.tables.presentation.components.TablesAppBar
import com.example.foodicsassessment.features.tables.presentation.components.UserDetailsBar
import com.example.foodicsassessment.features.tables.presentation.components.ViewOrderComponent
import com.example.foodicsassessment.features.tables.presentation.contract.TablesUiEvents
import com.example.foodicsassessment.features.tables.presentation.contract.TablesUiState
import com.example.foodicsassessment.features.tables.presentation.viewModel.TablesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun TablesScreenRoot(
    modifier: Modifier = Modifier, handleCommonEvents: (CommonUiEffect) -> Unit
) {
    val viewmodel: TablesViewModel = koinViewModel()
    viewmodel.ObserveLifecycleEvents(lifecycle = LocalLifecycleOwner.current.lifecycle)

    ObserveAsEvents(flow = viewmodel.effectFlow) { event ->
        when (event) {
            is CommonUiEffect -> {
                handleCommonEvents(event)
            }
        }
    }

    TablesScreen(
        uiState = viewmodel.viewState,
        onEvent = viewmodel::onEvent,
        modifier = modifier,
        handleCommonEvents = handleCommonEvents

    )
}

@Composable
fun TablesScreen(
    modifier: Modifier = Modifier,
    uiState: TablesUiState,
    onEvent: (TablesUiEvents) -> Unit,
    handleCommonEvents: (CommonUiEffect) -> Unit,

    ) {
    val pagerState = rememberPagerState(initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { uiState.categories.size })
    val coroutineScope = rememberCoroutineScope()
    val validIndex = uiState.selectedTabIndex.takeIf { it in uiState.categories.indices } ?: 0
    if (uiState.loading) {
        MainAppLoader(modifier = Modifier.background(Color.White))
    } else {
        Column(
            modifier = modifier
                .background(
                    color = MaterialTheme.colors.backgroundColor
                )
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.small)
        ) {
            UserDetailsBar(
                userName = uiState.userName, userId = uiState.userId
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            TablesAppBar(tablesNumber = uiState.tablesCount, usersNumber = uiState.peopleCount)
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            CommonTextField(
                value = uiState.searchKey,
                placeHolder = stringResource(id = R.string.search_place_holder),
                onValueChange = {
                    onEvent(TablesUiEvents.OnSearchKeyChanged(it))
                },
                prefixIcon = Icons.Default.Search
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            ScrollableTabRow(divider = {},
                edgePadding = 0.dp,
                containerColor = Color.Unspecified,
                selectedTabIndex = validIndex,
                indicator = { tabPositions ->
                    Row(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[validIndex]),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .height(3.dp)
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.primary, shape = CircleShape
                                )
                        )
                    }
                }) {
                uiState.categories.forEachIndexed { index, title ->
                    Tab(selected = index == validIndex, onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                            onEvent(TablesUiEvents.OnCategorySelected(index))
                        }
                    }, text = {
                        Text(
                            title.name.orEmpty(),
                            style = MaterialTheme.typography.small_text.copy(
                                color = MaterialTheme.colors.textColor,
                                fontWeight = if (index == uiState.selectedTabIndex) {
                                    FontWeight.SemiBold
                                } else {
                                    FontWeight.Normal
                                }

                            )
                        )
                    })
                }
            }

            // HorizontalPager to switch between pages
            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) { page ->
                // Content for each page (replace with your content composable)
                ProductListComponent(
                    categoryId = uiState.categories[page].id ?: -1,
                    searchKey = uiState.searchKey,
                    handleCommonEvents = handleCommonEvents,
                    onProductClicked = {
                        onEvent(TablesUiEvents.AddToCart(it))
                    },
                )
            }

            if (uiState.cartItems.isNotEmpty()) {
                ViewOrderComponent(
                    totalPrice = uiState.cartItems.sumOf {
                        (it.amount * (it.product.price ?: 1.0))
                    },
                    totalCounter = uiState.cartItems.size,
                    onCheckoutClick = {
                        onEvent(TablesUiEvents.OnCheckoutClicked)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TablesScreenPreview(modifier: Modifier = Modifier) {
    TablesScreen(uiState = TablesUiState(
        loading = false, categories = listOf(
            Category(
                name = "Pizza", id = 1
            ), Category(
                name = "Burger", id = 2
            ), Category(
                name = "Sushi", id = 3
            ), Category(
                name = "Pizza", id = 1
            ), Category(
                name = "Burger", id = 2
            ), Category(
                name = "Sushi", id = 3
            )
        ), userName = "Eslam Samy", userId = "162845"
    ), onEvent = {}, modifier = modifier, handleCommonEvents = {}

    )


}