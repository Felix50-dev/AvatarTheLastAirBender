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
import com.example.avatarthelastairbender.presentation.character_detail.TwoColorSurface
import com.example.avatarthelastairbender.presentation.character_detail.TwoColorSurfaceEarth

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val CHARACTER_DETAIL_ROUTE = "character"
    const val CHARACTER_ID_KEY = "_id"
    const val ROUTE_WITH_ARGS = "$CHARACTER_DETAIL_ROUTE/{$CHARACTER_ID_KEY}"
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
                onCharacterClick = {
                    navController.navigate("${MainDestinations.CHARACTER_DETAIL_ROUTE}/${it}")
                }
            )
        }
        composable(
            route = MainDestinations.ROUTE_WITH_ARGS,
            arguments = listOf(navArgument(MainDestinations.CHARACTER_ID_KEY) {
                type = NavType.StringType
            })
        ) {
            TwoColorSurface(colors = listOf(Color.Blue, Color.White) )
        }
        composable(
            route = MainDestinations.ROUTE_WITH_ARGS,
            arguments = listOf(navArgument(MainDestinations.CHARACTER_ID_KEY) {
                type = NavType.StringType
            })
        ) {
            TwoColorSurfaceEarth(colors = listOf(Color(0xFF964B00), Color.Green) )
        }
    }
}