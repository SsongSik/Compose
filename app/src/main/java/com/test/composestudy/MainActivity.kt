package com.test.composestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.composestudy.ui.theme.ComposestudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                Greeting("World!")
                ButtonExample(onButtonClicked = {
                    Toast.makeText(this, "Send Clicked.", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun ButtonExample(onButtonClicked : () -> Unit) {
    //Button
    Button(
        onClick = onButtonClicked,
        enabled = true,
        border = BorderStroke(10.dp, Color.Magenta),
        shape = CircleShape,
        contentPadding = PaddingValues(50.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = "send",
        )
        Spacer(
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Text(text = "Send")
    }
}

@Composable
fun Greeting(name: String) {
    //Text
    Text(
        modifier = Modifier.size(300.dp),
        color = Color(0xffff9944),
        text = "Hello! $name!\nHello! $name!\nHello! $name!",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        letterSpacing = 2.sp,
        maxLines = 2,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposestudyTheme {
        Greeting("Android")
        ButtonExample(onButtonClicked = { })
    }
}