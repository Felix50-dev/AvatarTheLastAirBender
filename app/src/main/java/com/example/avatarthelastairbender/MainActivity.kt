package com.example.avatarthelastairbender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.avatarthelastairbender.navigation.AvatarNavHost
import com.example.avatarthelastairbender.presentation.avatar_list.AvatarListViewModel
import com.example.avatarthelastairbender.presentation.character_detail.CharacterDetailViewModel
import com.example.avatarthelastairbender.ui.theme.AvatarTheLastAirBenderTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvatarTheLastAirBenderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val avatarListViewModel: AvatarListViewModel by viewModels()

                    AvatarNavHost(
                        navController = rememberNavController(),
                        viewModel = avatarListViewModel,
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AvatarTheLastAirBenderTheme {
        Greeting("Android")
    }
}