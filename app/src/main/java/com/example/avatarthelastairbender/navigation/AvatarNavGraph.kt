package com.example.avatarthelastairbender.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.avatarthelastairbender.presentation.avatar_list.AvatarListViewModel
import com.example.avatarthelastairbender.presentation.avatar_list.MainScreen
import com.example.avatarthelastairbender.presentation.avatar_list.TwoColorSurface

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val CHARACTER_DETAIL_ROUTE = "snack"
    const val CHARACTER_ID_KEY = "snackId"
}

@Composable
fun AvatarNavHost(
    navController: NavHostController,
    viewModel: AvatarListViewModel,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = MainDestinations.HOME_ROUTE,
        modifier = modifier
    ) {
        composable(route = MainDestinations.HOME_ROUTE) {
            MainScreen(
                viewModel = viewModel,
            )
        }
        composable(
            route = MainDestinations.CHARACTER_DETAIL_ROUTE,
            arguments = listOf(navArgument(MainDestinations.CHARACTER_ID_KEY) {
                type = NavType.StringType
            })
        ) {
            //TwoColorSurface(topColor = Color.Blue , bottomColor = Color.White, characterAffiliation = )
        }
    }
}