package com.test.composestudy.part1.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import com.test.composestudy.R
import com.test.composestudy.ui.theme.ComposestudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ComposestudyTheme {
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
//                CheckBoxEx()
//                Greeting4()
//                TopBarEx("Android")
//                SlotEx()
//                ScaffoldEx()
                CatalogEx(items)
//            }
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.title,
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            Text(
                text = itemData.title,
                style = MaterialTheme.typography.h4
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            Text(
                text = itemData.description
            )
        }
    }
}

@Composable
fun CatalogEx(itemList: List<ItemData>) {
    LazyColumn {
        items(itemList.size) { item ->
            Item(itemList[item])
        }
    }
}

data class ItemData(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String,
)

val items = listOf(
    ItemData(
        imageId = R.drawable.image,
        title = "해변 놀이 공원",
        description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.image,
        title = "캐년",
        description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.image,
        title = "워터월드",
        description = "워터월드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.image,
        title = "미국의 캐년",
        description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.image,
        title = "라스베가스",
        description = "라스베가스입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
    ItemData(
        imageId = R.drawable.image,
        title = "호르슈 밴드",
        description = "호르슈 밴드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
    ),
)

@Composable
fun CheckBoxWithContent(
    checked: Boolean,
    toggleState : () -> Unit,
    content : @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable{ toggleState() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                toggleState()
            }
        )
        content()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldEx() {
    var checked by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            navigationIcon = {
                 IconButton(
                        onClick = { }
                    ) {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
            },
            title = {
                Text(text = "Scaffold App")
            }
        )
    },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {

            }
        }
        ) {
        Surface(modifier = Modifier.padding(8.dp)) {
            CheckBoxWithContent(
                checked = checked,
                toggleState = { checked = !checked }
            ) {
                Text(text = "컴포즈를 좋아합니다.")
            }
        }
    }
}

@Composable
fun CheckboxWithSlot(
    checked : Boolean,
    onCheckedChanged : () -> Unit,
    content : @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = {
                onCheckedChanged()
            }
        )
        content()
    }
}

@Composable
fun SlotEx() {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }

    Column {
        CheckboxWithSlot(checked = checked1, onCheckedChanged = {
            checked1 = !checked1
        }) {
            Text("텍스트1")
        }
        CheckboxWithSlot(checked = checked2, onCheckedChanged = {
            checked2 = !checked2
        }) {
            Text("텍스트2")
        }
    }
}

@Composable
fun TopBarEx(name : String) {
    Column(

    ) {
        TopAppBar(
            title = {
                Text(text = "TopBarEx")
            },
            navigationIcon = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "앱 네비게이션"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "좋아요"
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "설정"
                    )
                }
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "검색"
                    )
                }
            }
        )

        TopAppBar {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "앱 네비게이션"
                )
            }
            Text(text = "TopBarEx", modifier = Modifier.weight(1f))
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "좋아요"
                )
            }
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "설정"
                )
            }
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "검색"
                )
            }
        }

        Text(text = "Hello $name")
    }
}

@Composable
fun Greeting4() {
    var name by remember { mutableStateOf("Tom")}
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
//        TextField(
//            value = name,
//            onValueChange = {name = it}
//        )
        OutlinedTextField(
            value = name,
            label = {
                    Text(text = "이름")
            },
            onValueChange = {name = it}
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )

        Text(text = "Hello $name")
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
//    ComposestudyTheme {
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
//        Greeting4()
//        ScaffoldEx()
//    }
}