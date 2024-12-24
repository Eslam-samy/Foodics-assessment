import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodicsassessment.core.CommonUiEffect
import com.example.foodicsassessment.features.home_tabs.presentation.HomeTabsScreenRoot
import org.koin.androidx.compose.koinViewModel


@Composable
fun FoodicsAppNavigation(
    navController: NavHostController,
    handleCommonEvents: (CommonUiEffect) -> Unit,

    ) {
    NavHost(navController = navController, startDestination = HomeTabsRoute) {

        composable<HomeTabsRoute> {
            HomeTabsScreenRoot(
                handleCommonEvents = handleCommonEvents
            )
        }
    }
}


// here to use shared view model across screens
@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController,
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )

}