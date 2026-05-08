package com.example.piguzhongjian0508.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.piguzhongjian0508.screens.HealthScreen
import com.example.piguzhongjian0508.screens.PrivacySettingsScreen
import com.example.piguzhongjian0508.screens.ProfileScreen
import com.example.piguzhongjian0508.screens.SettingsScreen
import com.example.piguzhongjian0508.state.ButtCrackViewModel
import com.example.piguzhongjian0508.ui.components.SoftBottomTabItem
import com.example.piguzhongjian0508.ui.components.SoftSurface
import com.example.piguzhongjian0508.ui.theme.SoftBackground

object AppRoute {
    const val Health = "health"
    const val Profile = "profile"
    const val Settings = "settings"
    const val PrivacySettings = "privacy_settings"
}

private data class BottomTab(
    val route: String,
    val title: String,
    val isSelected: (NavDestination?) -> Boolean,
    val icon: @Composable (Color) -> Unit,
)

@Composable
fun PiguzhongjianApp(
    navController: NavHostController = rememberNavController(),
    buttCrackViewModel: ButtCrackViewModel = viewModel(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val isButtCrackClosed by buttCrackViewModel.isButtCrackClosed.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            MainBottomBar(
                currentDestination = currentDestination,
                isButtCrackClosed = isButtCrackClosed,
                onTabClick = { route -> navController.navigateToRootTab(route) },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.Health,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            composable(AppRoute.Health) {
                HealthScreen(viewModel = buttCrackViewModel)
            }
            composable(AppRoute.Profile) {
                ProfileScreen(onOpenSettings = { navController.navigate(AppRoute.Settings) })
            }
            composable(AppRoute.Settings) {
                SettingsScreen(
                    onBack = { navController.popBackStack() },
                    onOpenPrivacySettings = { navController.navigate(AppRoute.PrivacySettings) },
                )
            }
            composable(AppRoute.PrivacySettings) {
                PrivacySettingsScreen(
                    viewModel = buttCrackViewModel,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}

@Composable
private fun MainBottomBar(
    currentDestination: NavDestination?,
    isButtCrackClosed: Boolean,
    onTabClick: (String) -> Unit,
) {
    val profileRoutes = setOf(AppRoute.Profile, AppRoute.Settings, AppRoute.PrivacySettings)
    val tabs = listOf(
        BottomTab(
            route = AppRoute.Health,
            title = "健康状况",
            isSelected = { destination ->
                destination?.hierarchy?.any { it.route == AppRoute.Health } == true
            },
            icon = {
                Icon(
                    imageVector = if (isButtCrackClosed) {
                        Icons.Filled.HeartBroken
                    } else {
                        Icons.Filled.Favorite
                    },
                    contentDescription = "健康状况",
                    tint = if (isButtCrackClosed) {
                        Color(0xFFE53935)
                    } else {
                        Color(0xFF2E7D32)
                    },
                )
            },
        ),
        BottomTab(
            route = AppRoute.Profile,
            title = "个人中心",
            isSelected = { destination ->
                destination?.hierarchy?.any { it.route in profileRoutes } == true
            },
            icon = { tint ->
                Icon(Icons.Filled.Person, contentDescription = "个人中心", tint = tint)
            },
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(SoftBackground)
            .padding(horizontal = 18.dp, vertical = 12.dp),
    ) {
        SoftSurface(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp),
            shape = RoundedCornerShape(32.dp),
            cornerRadius = 32.dp,
            contentPadding = PaddingValues(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                tabs.forEach { tab ->
                    SoftBottomTabItem(
                        selected = tab.isSelected(currentDestination),
                        title = tab.title,
                        onClick = { onTabClick(tab.route) },
                        icon = tab.icon,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

private fun NavHostController.navigateToRootTab(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            inclusive = false
        }
        launchSingleTop = true
        restoreState = false
    }
}
