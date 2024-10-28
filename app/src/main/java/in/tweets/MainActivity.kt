package `in`.tweets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import `in`.tweets.screens.CategoryScreen
import `in`.tweets.screens.DetailScreen
import `in`.tweets.ui.theme.TweetsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Tweetsy")
                        })
                    }, backgroundColor = Color.Red, contentColor = Color.White) { innerPadding ->
                         Box(modifier = Modifier.padding(innerPadding)){
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category"){
                    type = NavType.StringType
                }
            )
        ){
            DetailScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetsTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = "Tweetsy")
                })
            },
            backgroundColor = Color.Black,
            contentColor = Color.White
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                App() // Preview the App composable
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    TweetsTheme {
        CategoryScreen {
            // Simulate navigation by doing nothing or use a mock navController
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    TweetsTheme {
        DetailScreen() // Make sure this function can handle missing arguments or use default values
    }
}