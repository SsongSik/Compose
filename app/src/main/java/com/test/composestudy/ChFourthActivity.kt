package com.test.composestudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.*
import coil.compose.AsyncImage
import com.test.composestudy.ui.theme.ComposestudyTheme
import kotlinx.coroutines.launch

class ChFourthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
//                ConstraintLayoutEx()
//                ConstraintSetEx()
//                ConstraintLayoutEx2()
//                CardEx(cardData)
//                CanvasEx()
//                DialogEx()
//                CustomDialogEx()
//                DropDownMenuEx()
//                SnackbarEx()
//                BottomAppBarEx()
//                PyeongToSquareMeterEx()
                AnimationEx()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationEx() {
    var helloWorldVisible by remember { mutableStateOf(true) }
    var inRed by remember { mutableStateOf(false) }

//    val backgroundColor = Color.LightGray
    val state by animateColorAsState(
        targetValue = if(inRed) Color.Red else Color.White
    )

    val alpha by animateFloatAsState(
        targetValue = if(inRed) 1.0f else 0.5f
    )

    Column (
        modifier = Modifier
            .padding(16.dp)
            .background(state)
            .alpha(alpha)
    ) {
        AnimatedVisibility(
            visible = helloWorldVisible,
            enter = slideInHorizontally() + expandHorizontally() + fadeIn(),
            exit = shrinkHorizontally() + fadeOut()
        ) {
            Text(text = "Hello World")
        }

        Row(
            Modifier.selectable(
                selected = helloWorldVisible,
                onClick = {
                    helloWorldVisible = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = helloWorldVisible,
                onClick = {helloWorldVisible = true}
            )
            Text(
                text = "Hello World 보이기"
            )
        }
        Row(
            Modifier.selectable(
                selected = !helloWorldVisible,
                onClick = {
                    helloWorldVisible = false
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !helloWorldVisible,
                onClick = {helloWorldVisible = false}
            )
            Text(
                text = "Hello World 감추기"
            )
        }

        Text(text = "배경색을 바꾸어봅시다.")
        Row(
            Modifier.selectable(
                selected = inRed,
                onClick = {
                    inRed = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = inRed,
                onClick = {inRed = true}
            )
            Text(
                text = "빨간색"
            )
        }
        Row(
            Modifier.selectable(
                selected = !inRed,
                onClick = {
                    inRed = false
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !inRed,
                onClick = {inRed = false}
            )
            Text(
                text = "흰색"
            )
        }
    }
}

@Composable
fun PyeongToSquareMeterEx() {
    // 추 후에 ViewModel 로 State 를 관리할 수 있음
    var pyeong by rememberSaveable{ mutableStateOf("23") }
    var squareMeter by rememberSaveable { mutableStateOf((23 * 3.306).toString()) }

    //State Hoisting
    PyeongToSquareMeterStateless(
        pyeong,
        squareMeter,
    ) {
        if(it.isBlank()) { // 평이 비어있을 때
            pyeong = ""
            squareMeter = ""
            return@PyeongToSquareMeterStateless
        }
        // Float 형태가 아닌 다른 값을 입력할 경우, 입력되지 못하게
        val numbericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
        pyeong = it
        squareMeter = (numbericValue * 3.306).toString()
    }

//    Column (
//        modifier = Modifier.padding(16.dp)
//    ) {
//        OutlinedTextField(
//            value = pyeong,
//            onValueChange = {
//                if(it.isBlank()) { // 평이 비어있을 때
//                    pyeong = ""
//                    squareMeter = ""
//                    return@OutlinedTextField
//                }
//                // Float 형태가 아닌 다른 값을 입력할 경우, 입력되지 못하게
//                val numbericValue = it.toFloatOrNull() ?: return@OutlinedTextField
//                pyeong = it
//                squareMeter = (numbericValue * 3.306).toString()
//            },
//            label = {
//                Text(text = "평")
//            }
//        )
//        OutlinedTextField(
//            value = squareMeter,
//            onValueChange = {
//                squareMeter = it
//            },
//            label = {
//                Text(text = "제곱미터")
//            }
//        )
//    }
}

// Ui 부분은 상태를 가지지 않음
@Composable
fun PyeongToSquareMeterStateless( // 상태에 대해서 절대 모름, 테스트에서 자유롭게 사용할 수 있음
    pyeong : String,
    squareMeter : String,
    onPyeongChange : (String) -> Unit
) {
    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange,
            label = {
                Text(text = "평")
            }
        )
        OutlinedTextField(
            value = squareMeter,
            onValueChange = {},
            label = {
                Text(text = "제곱미터")
            }
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomAppBarEx() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    var counter by remember { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar(

            ) {
                Text(text = "BottomAppBar")
                Button(onClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Hello"
                        )
                    }
                }) {
                    Text(text = "Show Snackbar")
                }
                Button(
                    onClick = {
                        counter++
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Counter: $counter"
                            )
                        }
                    }
                ) {
                    Text(text = "더하기")
                }
                Button(
                    onClick = {
                        counter--
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Counter: $counter"
                            )
                        }
                    }
                ) {
                    Text(text = "빼기")
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "counter = ${counter}회입니다.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SnackbarEx() {
    var counter by remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope() //SnackBar 가 suspend 함수이기 때문에 코루틴 스코프를 사용해야 한다.

//    val scaffoldState = rememberScaffoldState()

    val snackbarHostState = remember { SnackbarHostState()}
    val scaffoldState = rememberScaffoldState(
        snackbarHostState = snackbarHostState
    ) //이런식으로도 사용 가능
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Button(
            onClick = {
                counter++
                coroutineScope.launch {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = "Counter: $counter",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )
//                    when (result) {
//                        SnackbarResult.Dismissed -> {
//                            counter--
//                        }
//                        SnackbarResult.ActionPerformed -> {
//                            counter--
//                        }
//                    }
                }
            }
        ) {
            Text(text = "더하기")
        }
    }
}

@Composable
fun DropDownMenuEx() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(
            onClick = {expandDropDownMenu = true}
        ) {
            Text(text = "Show DropDownMenu")
        }
        Text(text = "Counter: $counter")
    }

    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = {
            expandDropDownMenu = false
        }
    ) {
        DropdownMenuItem(
            onClick = {
                counter++
                expandDropDownMenu = false
            }
        ) {
            Text(text = "더하기")
        }
        DropdownMenuItem(
            onClick = {
                counter--
                expandDropDownMenu = false
            }
        ) {
            Text(text = "빼기")
        }
    }
}

@Composable
fun CustomDialogEx() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column(

    ) {
        Button(
            onClick = {
                openDialog = true
            }
        ) {
            Text(text = "Show Dialog")
        }
        Text(text = "Counter: $counter")
    }

    if(openDialog) {
        Dialog(
            onDismissRequest = {
                openDialog = false
            },
        ) {
            Surface {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "버튼을 클릭해주세요.\n+1을 누르면 값이 증가합니다.\n-1을 누르면 값이 감소합니다.")
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ){
                        Button(
                            onClick = {
                                openDialog = false
                            }
                        ) {
                            Text(text = "취소")
                        }
                        Button(
                            onClick = {
                                counter++
                                openDialog = false
                            }
                        ) {
                            Text(text = "+1")
                        }
                        Button(
                            onClick = {
                                counter--
                                openDialog = false
                            }
                        ) {
                            Text(text = "-1")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DialogEx() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column(

    ) {
        Button(
            onClick = {
                openDialog = true
            }
        ) {
            Text(text = "Show Dialog")
        }
        Text(text = "Counter: $counter")
    }

    if(openDialog) {
        AlertDialog(
            onDismissRequest = { // dialog 외부
                openDialog = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        counter++
                        openDialog = false
                    }
                ) {
                    Text(text = "더하기")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        openDialog = false
                    }
                ) {
                    Text(text = "취소")
                }
            },
            title = {
                Text(text = "더하기")
            },
            text = {
                Text(text = "더하기 버튼을 누르면 카운터를 증가합니다.\n버튼을 눌러주세요.")
            }
        )
    }
}

@Composable
fun CanvasEx() {
    Canvas(modifier = Modifier.size(20.dp)) {
        drawLine(Color.Red, Offset(30f, 10f), Offset(50f, 40f))

        drawCircle(Color.Yellow, 10f, Offset(25f, 40f))
        drawRect(Color.Magenta, Offset(25f, 40f), Size(10f, 10f))

//        Icons.Filled.Send
        drawLine(Color.Green, Offset(2.01f, 21.0f), Offset(23.0f, 12.0f))
        drawLine(Color.Green, Offset(23.0f, 12.0f), Offset(2.01f, 3.0f))
    }
}

@Composable
fun CardEx2(cardData: CardData) {
    val placeHolderColor = Color(0x330000000)

    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (profileImage, author, description) = createRefs()
            AsyncImage(
                model = cardData.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = cardData.imageDescription,
                placeholder = ColorPainter(placeHolderColor),
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .constrainAs(profileImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 8.dp)
                    }
            )

            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author) {
                    linkTo(
                        profileImage.end,
                        parent.end,
                        startMargin = 8.dp,
                        endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(
                        profileImage.end,
                        parent.end,
                        startMargin = 8.dp,
                        endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )

            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)
            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }
    }
}

@Composable
fun ConstraintLayoutEx2() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (redBox, magentaBox, greenBox, text) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, margin = 18.dp)
                }
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(parent.top, margin = 32.dp)
                }
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    top.linkTo(parent.top, margin = 20.dp)
                }
        ) {

        }

        createVerticalChain(redBox, magentaBox, greenBox, chainStyle = ChainStyle.SpreadInside)
        val barrier = createEndBarrier(redBox, magentaBox, greenBox)
        Text(
            text = "가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사",
            modifier = Modifier
                .width(100.dp)
                .constrainAs(text) {
                    start.linkTo(barrier)
                    bottom.linkTo(magentaBox.bottom)
                }
        )
//        createHorizontalChain(redBox, magentaBox, greenBox, chainStyle = ChainStyle.SpreadInside)
//        val barrier = createBottomBarrier(redBox, magentaBox, greenBox)
//        Text(
//            text = "가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사 가나다라마바사",
//            modifier = Modifier.constrainAs(text) {
//                top.linkTo(barrier, margin = 20.dp)
//            }
//        )
    }
}

@Composable
fun ConstraintSetEx() {
    val constraintSet = ConstraintSet {
        val redBox = createRefFor("redBox")
        val magentaBox = createRefFor("magentaBox")
        val greenBox = createRefFor("greenBox")
        val yellowBox = createRefFor("yellowBox")

        constrain(redBox) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            end.linkTo(parent.end, margin = 4.dp)
        }

        constrain(magentaBox) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(greenBox) {
            centerTo(parent)
        }

        constrain(yellowBox) {
            start.linkTo(greenBox.end)
            top.linkTo(greenBox.bottom)
        }
    }

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .layoutId("redBox")
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .layoutId("magentaBox")
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .layoutId("yellowBox")
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .layoutId("greenBox")
        ) {

        }
    }
}

@Composable
fun ConstraintLayoutEx() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 4.dp)
                }
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    start.linkTo(magentaBox.end)
                    top.linkTo(magentaBox.bottom)
                }
        ) {

        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    top.linkTo(parent.top)
//                    bottom.linkTo(parent.bottom)
                    centerTo(parent)
                }
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCh4() {
    ComposestudyTheme {
//        ConstraintLayoutEx()
//        ConstraintSetEx()
//        ConstraintLayoutEx2()
//        CardEx2(ProfileCardActivity.cardData)
//        CanvasEx()
//        DialogEx()
//        CustomDialogEx()
//        DropDownMenuEx()
//        SnackbarEx()
//        BottomAppBarEx()
//        PyeongToSquareMeterEx()
        AnimationEx()
    }
}