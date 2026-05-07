package com.example.piguzhongjian0508.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
    val icon: @Composable () -> Unit,
)

@Composable
fun PiguzhongjianApp(
    navController: NavHostController = rememberNavController(),
    buttCrackViewModel: ButtCrackViewModel = viewModel(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            MainBottomBar(
                currentDestination = currentDestination,
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
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "健康状况") },
        ),
        BottomTab(
            route = AppRoute.Profile,
            title = "个人中心",
            isSelected = { destination ->
                destination?.hierarchy?.any { it.route in profileRoutes } == true
            },
            icon = { Icon(Icons.Filled.Person, contentDescription = "个人中心") },
        ),
    )

    NavigationBar {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = tab.isSelected(currentDestination),
                onClick = { onTabClick(tab.route) },
                icon = tab.icon,
                label = { Text(tab.title) },
            )
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
