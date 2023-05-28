package com.test.composestudy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
//                Greeting("World!")
//                ButtonExample(onButtonClicked = {
//                    Toast.makeText(this, "Send Clicked.", Toast.LENGTH_SHORT).show()
//                })
                ModifierEx()
            }
        }
    }
}

@Composable
fun ModifierEx() {
    Button(
        onClick = {},
//        modifier = Modifier.fillMaxSize()
        modifier = Modifier
            .height(100.dp)
            .width(200.dp)
//            .size(200.dp, 200.dp)
//            .background(Color.Red), //modifier 로 버튼의 색상을 바꿀 수는 없음,
            .padding(10.dp),
         colors = ButtonDefaults.buttonColors(
             backgroundColor = Color.Magenta,
             contentColor = Color.Cyan
         ),
//        enabled = false
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier.background(Color.Blue)
        )
        Spacer(
            modifier = Modifier
                .size(ButtonDefaults.IconSpacing)
                .background(Color.Blue)
        )
        Text(
            "Search",
            modifier = Modifier
                .offset(x = 10.dp)
                .background(Color.Blue)
//            modifier = Modifier.clickable {  }
        )
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
        contentPadding = PaddingValues(30.dp)
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
//        Greeting("Android")
//        ButtonExample(onButtonClicked = { })
        ModifierEx()
    }
}