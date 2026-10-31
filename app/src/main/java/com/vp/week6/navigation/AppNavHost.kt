package com.vp.week6.navigation

import android.net.http.SslCertificate.restoreState
import android.net.http.SslCertificate.saveState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vp.week6.ui.screen.ClubProjectsScreen
import com.vp.week6.ui.screen.CommunitiesScreen
import com.vp.week6.ui.screen.DashboardScreen
import com.vp.week6.ui.screen.ProjectDetailScreen
import com.vp.week6.viewmodel.CampusViewModel


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val sharedViewModel: CampusViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.hasRoute<DashboardRoute>() == true ||
            currentDestination?.hasRoute<CommunitiesRoute>() == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = androidx.compose.ui.graphics.Color.White
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
                        label = { Text("Dashboard") },
                        selected = currentDestination?.hasRoute<DashboardRoute>() == true,
                        onClick = {
                            navController.navigate(DashboardRoute) {
                                popUpTo(DashboardRoute) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = androidx.compose.ui.graphics.Color(0xFF1E50FF),
                            selectedTextColor = androidx.compose.ui.graphics.Color.Black,
                            indicatorColor = androidx.compose.ui.graphics.Color(0xFFE3F2FD),
                            unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                            unselectedTextColor = androidx.compose.ui.graphics.Color.Gray
                        )
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.List, contentDescription = "Communities") },
                        label = { Text("Communities") },
                        selected = currentDestination?.hasRoute<CommunitiesRoute>() == true,
                        onClick = {
                            navController.navigate(CommunitiesRoute) {
                                popUpTo(DashboardRoute) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = androidx.compose.ui.graphics.Color(0xFF1E50FF),
                            selectedTextColor = androidx.compose.ui.graphics.Color.Black,
                            indicatorColor = androidx.compose.ui.graphics.Color(0xFFE3F2FD),
                            unselectedIconColor = androidx.compose.ui.graphics.Color.Gray,
                            unselectedTextColor = androidx.compose.ui.graphics.Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DashboardRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<DashboardRoute> {
                DashboardScreen(viewModel = sharedViewModel)
            }

            composable<CommunitiesRoute> {
                CommunitiesScreen(
                    viewModel = sharedViewModel,
                    onNavigateToDetail = { communityId ->
                        navController.navigate(ClubProjectsRoute(communityId))
                    }
                )
            }

            composable<ClubProjectsRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<ClubProjectsRoute>()
                ClubProjectsScreen(
                    communityId = route.communityId,
                    viewModel = sharedViewModel,
                    onBack = { navController.popBackStack() },
                    onNavigateToProject = { projectId ->
                        navController.navigate(ProjectDetailRoute(projectId))
                    }
                )
            }

            composable<ProjectDetailRoute> { backStackEntry ->
                val route = backStackEntry.toRoute<ProjectDetailRoute>()
                ProjectDetailScreen(
                    projectId = route.projectId,
                    viewModel = sharedViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}