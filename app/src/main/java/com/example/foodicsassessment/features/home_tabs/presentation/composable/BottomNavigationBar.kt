import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodicsassessment.R
import com.example.foodicsassessment.core.presentation.ui.theme.MyApplicationTheme
import com.example.foodicsassessment.core.presentation.ui.theme.colors
import com.example.foodicsassessment.features.home_tabs.navigation.BottomNavItem
import com.example.foodicsassessment.features.home_tabs.navigation.MenuRoute
import com.example.foodicsassessment.features.home_tabs.navigation.OrdersRoute
import com.example.foodicsassessment.features.home_tabs.navigation.SettingsRoute
import com.example.foodicsassessment.features.home_tabs.navigation.TableRoute


@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier
            .background(color = Color.White)
            .navigationBarsPadding()
            .height(70.dp)
        ,
        containerColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val qualifiedNames = listOf(
            TableRoute::class.qualifiedName,
            OrdersRoute::class.qualifiedName,
            MenuRoute::class.qualifiedName,
            SettingsRoute::class.qualifiedName,
        )
        val items = listOf(
            BottomNavItem.Tables,
            BottomNavItem.Orders,
            BottomNavItem.Menu,
            BottomNavItem.Settings,
        )
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                colors =
                NavigationBarItemDefaults.colors().copy(
                    selectedIndicatorColor = Color.Transparent,
                ),
                selected = currentRoute == qualifiedNames[index],
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (currentRoute == qualifiedNames[index]) Color.Unspecified else MaterialTheme.colors.textColor.copy(
                                alpha = .3f
                            )
                        )
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = if (currentRoute == qualifiedNames[index]) Color.Unspecified else MaterialTheme.colors.textColor.copy(
                            alpha = .3f
                        )
                    )
                },
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    MyApplicationTheme {
        BottomNavigationBar(navController = NavController(LocalContext.current))
    }
}