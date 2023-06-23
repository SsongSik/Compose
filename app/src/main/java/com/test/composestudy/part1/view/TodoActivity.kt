package com.test.composestudy.part1.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.composestudy.ui.theme.ComposestudyTheme

class TodoActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ComposestudyTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    TopLevel()
//                }
//            }
        }
    }
}

class ToDoViewModel : ViewModel() {
    //viewModel에 상태를 분리하여 관리하여 remember를 사용하지 않아도 됨
//    val text = mutableStateOf("")
    private val _text = MutableLiveData("")
    val text : LiveData<String> get() = _text

    val setText : (String) -> Unit = {
        _text.value = it
    }

//    val toDoList = mutableStateListOf<ToDoData>()
    private val _rawToDoList = mutableListOf<ToDoData>()
    private val _toDoList = MutableLiveData<List<ToDoData>>(_rawToDoList)
    val toDoList : LiveData<List<ToDoData>> = _toDoList

    // mutableStateListOf 추가, 삭제, 변경 되었을 때만 Ui 갱신. 각 항목에 필드가 바뀌었을 때 갱신 x
    // LiveData<List<x>> -> List가 통채로 다른 List로 바뀌었을 때만 State가 갱신됨

    val onSubmit : (String) -> Unit = {it ->
        val key = (_rawToDoList.lastOrNull()?.key ?: 0) + 1
        _rawToDoList.add(ToDoData(key, it))
        _toDoList.value = _rawToDoList.toMutableList()
        _text.value = ""
//        setText("")
    }

    val onToggle : (Int, Boolean) -> Unit = { key, checked ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy( done = checked)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onDelete : (Int) -> Unit = { key ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList.removeAt(i)
        _toDoList.value = _rawToDoList.toMutableList()
    }

    val onEdit : (Int, String) -> Unit = { key, newText ->
        val i = _rawToDoList.indexOfFirst { it.key == key }
        _rawToDoList[i] = _rawToDoList[i].copy( text = newText)
        _toDoList.value = _rawToDoList.toMutableList()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopLevel(viewModel : ToDoViewModel = viewModel()) {
//    val (text, setText) = remember { mutableStateOf("") }
//    val toDoList = remember { mutableStateListOf<ToDoData>() }
    // MutableStateList 추가, 삭제, 변경 되었을 때만 Ui 갱신

//    val onSubmit : (String) -> Unit = {text ->
//        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
//        toDoList.add(ToDoData(key, text))
//        viewModel.text.value = ""
////        setText("")
//    }
//
//    val onToggle : (Int, Boolean) -> Unit = { key, checked ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList[i] = toDoList[i].copy( done = checked)
//    }
//
//    val onDelete : (Int) -> Unit = { key ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList.removeAt(i)
//    }
//
//    val onEdit : (Int, String) -> Unit = { key, newText ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList[i] = toDoList[i].copy( text = newText)
//    }

    Scaffold {
        Column {
            ToDoInput(
                //LiveData Observing 사용
                text = viewModel.text.observeAsState("").value,
                onTextChange = {
                   viewModel.setText(it)
                },
                onSubmit = viewModel.onSubmit
            )
            val item = viewModel.toDoList.observeAsState(emptyList<ToDoData>()).value
            LazyColumn{
                items(items = item, key = { it.key }) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp
    ) {
        Crossfade(targetState = isEditing) {
            when (it) {
                false -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = toDoData.text
                        )
                        Text(
                            text = "완료"
                        )
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )
                        Button(
                            onClick = {
                                isEditing = true
                            }
                        ) {
                            Text(text = "수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = {
                                onDelete(toDoData.key)
                            }
                        ) {
                            Text(text = "삭제")
                        }
                    }
                }
                true -> {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        var (newText, setNewText) = remember { mutableStateOf(toDoData.text) }

                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = {
                                onEdit(toDoData.key, newText)
                                isEditing = false
                            }
                        ) {
                            Text(text = "완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
//    ComposestudyTheme {
//        ToDoInput("테스트", {}, {})
//    }
}

@Preview(showBackground = true)
@Composable
fun DefaultTodoPreview() {
//    ComposestudyTheme {
//        TopLevel()
//    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
//    ComposestudyTheme {
//        ToDo(ToDoData(1, "nice", true))
//    }
}

//값을 바꿀 수 없는 형태
data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)