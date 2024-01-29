package com.example.avatarthelastairbender.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.avatarthelastairbender.R
import com.example.avatarthelastairbender.presentation.avatar_list.AvatarListViewModel
import com.example.avatarthelastairbender.presentation.avatar_list.HomeScreen
import com.example.avatarthelastairbender.presentation.character_detail.CharacterDetailScreen

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val CHARACTER_DETAIL_ROUTE = "character"
    const val CHARACTER_ID_KEY = "_id"
    const val CHARACTER_CATEGORY = "category"
    const val ROUTE_WITH_ARGS = "$CHARACTER_DETAIL_ROUTE/{$CHARACTER_ID_KEY}/{$CHARACTER_CATEGORY}"
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
            HomeScreen(
                viewModel = viewModel
            ) { characterId, characterName ->
                navController.navigate("${MainDestinations.CHARACTER_DETAIL_ROUTE}/${characterId}/${characterName}")
            }
        }
        composable(
            route = MainDestinations.ROUTE_WITH_ARGS,
            arguments = listOf(navArgument(MainDestinations.CHARACTER_ID_KEY) {
                type = NavType.StringType
            }, navArgument(MainDestinations.CHARACTER_CATEGORY) {
                type = NavType.StringType
            })
        ) {
            CharacterDetailScreen(
                navigateBack = {navController.navigateUp()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    if (canNavigateBack) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        fontSize = 24.sp
                    )
                }
            },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                }
            },
            scrollBehavior = scrollBehavior
        )
    } else {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = title,
                        fontSize = 24.sp
                    )
                }
            }
        )
    }
}