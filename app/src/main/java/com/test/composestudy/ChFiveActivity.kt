package com.test.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.composestudy.ui.theme.ComposestudyTheme

class ChFiveActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {

            }
        }
    }
}

val LocalElevation = compositionLocalOf { 8.dp }

@Composable
fun Greeting() {
    //암시적인 방법이기 때문에 예상하기 어려울 수 있음
    CompositionLocalProvider(LocalElevation provides 12.dp) {
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
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCh5() {
    ComposestudyTheme {
        Greeting()
    }
}