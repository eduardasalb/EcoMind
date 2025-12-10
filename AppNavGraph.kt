package com.example.ecomind.local

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecomind.model.ChallengesViewModel
import com.example.ecomind.model.ChallengesViewModelFactory
import com.example.ecomind.repository.ChallengeRepository

sealed class AppScreen(val route: String) {
    object Login : AppScreen("login")
    object Tips : AppScreen("tips")
    object History : AppScreen("history")
    object Challenges : AppScreen("challenges")
    object Progress : AppScreen("progress")
    object Profile : AppScreen("profile")
}

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {

    val repo = remember { ChallengeRepository() }
    val factory = remember { ChallengesViewModelFactory(repo) }
    val challengesViewModel: ChallengesViewModel = viewModel(factory = factory)

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ) {

        composable(AppScreen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppScreen.Tips.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppScreen.Tips.route) {
            TipsScreen(
                onHistoryClick = { navController.navigate(AppScreen.History.route) },
                onChallengesClick = { navController.navigate(AppScreen.Challenges.route) },
                onProgressClick = { navController.navigate(AppScreen.Progress.route) },
                onProfileClick = { navController.navigate(AppScreen.Profile.route) }
            )
        }

        composable(AppScreen.History.route) {
            HistoryScreen(onBackClick = { navController.popBackStack() })
        }

        composable(AppScreen.Challenges.route) {
            ChallengesScreen(
                viewModel = challengesViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.Progress.route) {
            ProgressScreen(
                viewModel = challengesViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(AppScreen.Profile.route) {
            ProfileScreen(
                viewModel = challengesViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
