package com.test.composestudy.part1.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.test.composestudy.ui.theme.ComposestudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChFiveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ComposestudyTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ){
////                    Greeting()
////                    MyNav()
////                    ReposScreen()
//                }
//            }
        }
    }
}

//@Composable
//fun ReposScreen(viewModel: GithubViewModel = viewModel()) {
//    LazyColumn {
//        item {
//            Button(onClick = {
//                viewModel.getRepos()
//            }) {
//                Text("리포지토리 가져오기")
//            }
//        }
//        items(viewModel.repos) {
//            Text(it.name)
//        }
//    }
//}
@Composable
fun MyNav(
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController, "Home", modifier = modifier) {
        composable("Home") {
            Column(

            ) {
                Text("Home")
                Button(
                    onClick = {
                        navController.navigate("Playground") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Playground로 이동")
                }
                Button(
                    onClick = {
                        navController.navigate("Office") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Office로 이동")
                }
                Button(
                    onClick = {
                        navController.navigate("Home") {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(text = "Home로 이동")
                }
                Button(
                    onClick = {
                        navController.navigate("Argument/1234"){
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(text = "MYONGSIK로 이동")
                }
            }
        }
        composable("Office") {
            Column(

            ) {
                Text("Office")
                Button(
                    onClick = {
                        navController.navigate("Playground") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Playground으로 이동")
                }
                Button(
                    onClick = {
                        navController.navigate("Home") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Home으로 이동")
                }
            }
        }
        composable("Playground") {
            Column(

            ) {
                Text("Playground")
                Button(
                    onClick = {
                        navController.navigate("Home") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Home으로 이동")
                }
                Button(
                    onClick = {
                        navController.navigate("Office") {
                            popUpTo("Home") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Office으로 이동")
                }
            }
        }
        composable(
            "Argument/{userId}"
        ) {
            val userId = it.arguments?.get("userId")
            Text("UserId : $userId")
        }
    }
}

val LocalElevation = compositionLocalOf { 8.dp }

@Composable
fun Greeting() {
    //암시적인 방법이기 때문에 예상하기 어려울 수 있음
//    CompositionLocalProvider(LocalElevation provides 12.dp) {
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = LocalElevation.current
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Hello World Hello World")
                Text("${LocalContentColor.current}")
                Text("Hello World Hello World")
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                    Text("Hello World Hello World")
                }
                CompositionLocalProvider(LocalContentColor provides Color.Magenta) {
                    Text("Hello World Hello World")
                    Text("${LocalContentColor.current}")
                }
                Text("Hello World Hello World")
                Text("Hello World Hello World")
                Text("Hello World Hello World")
                Text("Hello World Hello World")

                Button(
                    onClick = {}
                ) {
                    Text("Button")
                }
            }
        }
//    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCh5() {
//    ComposestudyTheme {
////        Greeting()
//        MyNav()
//    }
}