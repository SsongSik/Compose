package com.test.composestudy.part1.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.composestudy.ui.theme.ComposestudyTheme

class ProfileCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        CardEx(cardData)
                        CardEx(cardData)
                    }
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUrl = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part1-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg",
            imageDescription = "캐년",
            author = "Dalinaum",
            description = "캐년은 죽기전에 꼭 봐야할 절경으로 소개되었습니다."
        )
    }
}

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0x330000000)

    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = cardData.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = cardData.imageDescription,
                placeholder = ColorPainter(placeHolderColor),
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Column {
                Text(
                    text = cardData.author,
                )
                Text(
                    text = cardData.description
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardDefaultPreview() {
    ComposestudyTheme {
        CardEx(ProfileCardActivity.cardData)
    }
}

data class CardData(
    val imageUrl : String,
    val imageDescription : String,
    val author : String,
    val description : String
)