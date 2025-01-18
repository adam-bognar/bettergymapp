package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.bettergymapp.MainActivity
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine

@Composable
fun RoutineList(
    list: List<Routine>,
    colors: List<Color>,
    onStart: (Routine) -> Unit = {},
    onNavigateToExerciseAdd: (routine: Routine) -> Unit = {}
) {
    val cornerRadius = 35
    val buttonSize = 55.dp
    val paddingBetweenButtons = 15.dp

    val pagerState = rememberPagerState(pageCount = { list.size })
    val editSelected = remember { mutableStateOf(false) }
    val addExercise = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 0.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
            // Top part: HorizontalPager & Exercises
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                val currentList = remember { mutableStateOf(list[page].exercises) }

                Column {
                    RoutineCard(list[page].name, colors[page])

                    LazyColumn(modifier = Modifier.weight(1f)) {
                        if (addExercise.value) {
                            val tempList = mutableListOf<Exercise>()
                            tempList.add(Exercise(name = ""))

                            for (item in currentList.value) {
                                tempList.add(item)
                            }

                            items(tempList.size) { exercise ->
                                if (exercise == 0) {
                                    LaunchedEffect(Unit) { focusRequester.requestFocus() }
                                    ExerciseCardWithInput(
                                        tempList[0].name,
                                        focusRequester
                                    ) { newName ->
                                        tempList[0] = Exercise(name = newName)
                                        currentList.value += tempList[0]
                                        list[page].exercises = currentList.value
                                        CoroutineScope(Dispatchers.IO).launch {
                                            MainActivity.routineRepository.update(list[page])
                                        }
                                        addExercise.value = false
                                        focusManager.clearFocus()
                                    }
                                } else {
                                    ExerciseCard(tempList[exercise].name)
                                }
                            }
                        } else {
                            items(currentList.value.size) { exercise ->
                                if (editSelected.value) {
                                    ExerciseCardWithDelete(currentList.value[exercise].name) {
                                        currentList.value -= currentList.value[exercise]
                                        list[page].exercises = currentList.value
                                        CoroutineScope(Dispatchers.IO).launch {
                                            MainActivity.routineRepository.update(list[page])
                                        }
                                    }
                                } else {
                                    ExerciseCard(currentList.value[exercise].name)

                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottom part: Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            if (!editSelected.value) {

                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = paddingBetweenButtons)
                        .size(buttonSize),
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            MainActivity.routineRepository.delete(list[pagerState.currentPage])
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            if (!editSelected.value) {
                Button(
                    modifier = Modifier
                        .weight(2f)
                        .size(buttonSize),
                    onClick = { onStart(list[pagerState.currentPage]) },
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.happyblue))
                ) {
                    Text(text = "Start", color = Color.Black)
                }
            } else {
                Button(
                    modifier = Modifier
                        .weight(2f)
                        .size(buttonSize),
                    onClick = {
                        val id = list[pagerState.currentPage].id

                        onNavigateToExerciseAdd(list[pagerState.currentPage])
                    },
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.buttonColors(colorResource(id = R.color.happyblue))
                ) {
                    Text(text = "Add exercise", color = Color.Black)
                }
            }

            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = paddingBetweenButtons)
                    .size(buttonSize),
                onClick = {
                    editSelected.value = !editSelected.value
                    addExercise.value = false
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewRoutineList() {
    val Exercise = listOf(
        Exercise(name = "Exercise 1"),
        Exercise(name = "Exercise 2")
    )

    val sampleRoutines = listOf(
        Routine(name = "Routine 1", description = "", exercises = Exercise),
        Routine(name = "Routine 2", description = "", exercises = Exercise)
    )
    val sampleColors = listOf(Color.Red, Color.Blue)
    val pagerState = rememberPagerState(pageCount = { sampleRoutines.size })
    RoutineList(list = sampleRoutines, colors = sampleColors)
}