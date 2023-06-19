package com.test.composestudy.part1.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.composestudy.part1.screen.DetailScreen
import com.test.composestudy.part1.screen.MainScreen
import com.test.composestudy.ui.theme.ComposestudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    PokeTopLevel()
                }
            }
        }
    }
}

@Composable
fun PokeTopLevel(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        "Home",
        modifier = modifier
    ) {
        composable("Home") {
            MainScreen(
                onPokemonClick = {
                    val pokemonId = it.substringAfter("pokemon/")
                        .substringBefore("/")
                        .toInt()
                    navController.navigate("Detail/${pokemonId}")
                }
            )
        }

        composable(
            "Detail/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                }
            )
        ) {
            val pokemonId = it.arguments?.getInt("pokemonId") ?: 0
            DetailScreen(
                pokemonId = pokemonId,
                onUpButtonClick = {
                    navController.navigate("Home") {
                        popUpTo("Home") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewPokemon() {
    ComposestudyTheme {

    }
}