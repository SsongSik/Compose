package com.test.composestudy

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
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
//                ModifierEx()
//                Greeting2("World!")
//                BoxEx()
//                RowEx()
//                ColumnEx()
//                Outer()
//                Greeting3()
//                CoilEx()
                CheckBoxEx()
            }
        }
    }
}

//CheckBoxEx
//CheckBoxEx2 composable func
//setContent 두 개 모두 새로 그려질 수 있음
//Composable 은 동시에 여러 곳에서 그려질 수 있음
//mutableStateOf 와 remember 키워드를 같이
@Composable
fun CheckBoxEx() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        //mutableStateOf 를 바꾸어야함
        //var checked = false

        //destruction 비구조화, 반구조화
//        val (a, b) = listOf(2, 3)

//        var checked = remember{ mutableStateOf(false) }
//        Checkbox(
//            checked = checked.value, //상태를 바꾸어야함
//            onCheckedChange = {
//                checked.value = !checked.value
//            }
//        )
//        Text(
//            text = "프로그래머 입니까?"
//        )

        //위임된 속성
//        var checked by remember { mutableStateOf (false) }
//        Checkbox(
//            checked = checked, //상태를 바꾸어야함
//            onCheckedChange = {
//                checked = !checked
//            }
//        )
//        Text(
//            text = "프로그래머 입니까?"
//        )
        val (getChecked, setChecked) = remember { mutableStateOf (false) }
        Checkbox(
            checked = getChecked, //상태를 바꾸어야함
//            onCheckedChange = {
//                setChecked(it)
//            }
            onCheckedChange = setChecked
        )
        Text(
            text = "프로그래머 입니까?",
            modifier = Modifier.clickable {
                setChecked(!getChecked)
            }
        )
    }
}

@Composable
fun CoilEx() {
    //remember 뒤 내용들을 저장했다가 리컴포지션이 될 때 기억하기 위함
//    val painter = rememberImagePainter(data = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part1-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg")
//    Image(
//        painter = painter,
//        contentDescription = "캐년"
//    )

    Column{
        AsyncImage(
            model = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part1-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg",
            contentDescription = "캐년"
        )
        AsyncImage(
            model = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part1-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg",
            contentDescription = "캐년"
        )
    }
}

@Composable
fun Greeting3() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "이미지"
        )
        Image(
            imageVector = Icons.Filled.Settings,
            contentDescription = "세팅"
        )
//        Image( -> context 주입 필요
//            bitmap = ,
//            contentDescription = "이미지 비트맵"
//        )
    }
}

@Composable
fun Outer() {
    Column(
        modifier = Modifier.width(150.dp)
    ){
        Inner(modifier = Modifier
            .widthIn(min = 100.dp, max = 350.dp)
            .heightIn(min = 50.dp, max = 160.dp)
            .height(160.dp)
        )
        Inner(modifier = Modifier
            .widthIn(min = 100.dp, max = 350.dp)
            .heightIn(min = 50.dp, max = 160.dp)
            .height(100.dp)
        )
    }
}

@Composable
private fun Inner(modifier : Modifier = Modifier) {
    BoxWithConstraints(
        modifier
    ) {
        if(maxHeight > 150.dp) {
            Text(
                text = "여기 꽤 길군요!",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Text(text = "max : $maxWidth maxH : $maxHeight minW : $minWidth minH : $minHeight")
    }
}

@Composable
fun ColumnEx() {
    /*
    Alignment 는 진행방향과 수직으로
    Arrangement 는 진행방향으로
     */
    Column( //horizontal 하게만 align 할 수 있음, row 는 vertical 하게만 alignment 할 수 있음, box 는 자유
        modifier = Modifier.size(100.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        //ColumnScope 에만 맞는 alignment 를 사용해야함
        Text(
            text = "첫 번째",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(text = "두 번째")
        Text(
            text = "세 번째",
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Composable
fun RowEx() {
    Row(
        modifier = Modifier
            .height(40.dp)
            .width(200.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween, //Row 는 수평으로 가기 때문에 수평방향이 기준
    ) {
        Text(
            text = "첫 번째!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Top)
                .weight(3f) //비중
                .background(Color.Magenta)
        )
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "추가",
            modifier = Modifier
                .weight(1f)
                .background(Color.Cyan)
        )
//        Text(
//            text = "두 번째!",
//            modifier = Modifier
//                .weight(1f)
////            modifier = Modifier.align(Alignment.CenterVertically)
//        )
        Text(
            text = "세 번째!",
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(3f)
                .background(Color.Blue)
//            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}

@Composable
fun BoxEx() {
    // FrameLayout
    Box(
//        modifier = Modifier.size(100.dp)
    ) {
//        Text(
//            text = "Hello World",
//            modifier = Modifier.align(Alignment.BottomEnd) //align BoxScope 안에서만 사용 가능
//        )
//        Text(
//            text = "Jetpack",
//            modifier = Modifier.align(Alignment.CenterEnd)
//        )
//        Text(
//            text = "Compose",
//            modifier = Modifier.align(Alignment.TopStart)
//        )
        Box(modifier = Modifier
            .fillMaxSize()
//            .matchParentSize() //부모의 사이즈. 두 번째 자식을 보고 결정됨
            .background(color = Color.Cyan)
            .align(Alignment.CenterStart)
        )
        Box(modifier = Modifier
            .size(70.dp)
            .background(color = Color.Yellow)
            .align(Alignment.Center)
        )
    }
}

@Composable
fun Greeting2(name : String) {
    Surface(
        color = MaterialTheme.colors.secondary, //secondary 에 맞는 onSecondary 색상이 자연스레 content Color로 설정됨
        modifier = Modifier.padding(10.dp), //Text 의 Margin 을 주기 위함
        elevation = 5.dp,
        border = BorderStroke(
            width = 2.dp,
            color = Color.Magenta
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            text = "Hello $name",
            modifier = Modifier.padding(8.dp)
        )
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
//        ModifierEx()
//        Greeting2("World")
//        BoxEx()
//        RowEx()
//        ColumnEx()
//        BoxWithConstraints() {
//            Outer()
//        }
//        Greeting3()
//        CoilEx()
    }
}