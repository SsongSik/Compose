package com.test.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCh5() {
    ComposestudyTheme {

    }
}