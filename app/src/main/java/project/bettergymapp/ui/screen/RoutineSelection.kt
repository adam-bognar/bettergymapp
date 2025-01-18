package project.bettergymapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R
import project.bettergymapp.data.Routine
import project.bettergymapp.data.viewmodel.RoutineViewModel


@Composable
fun RoutineSelection(
    onNavigateToRoutineAdd: () -> Unit,
    onNavigateToExerciseAdd: (routine: Routine) -> Unit,
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory),
    onStart: (Routine) -> Unit = {},
) {
    val list = viewModel.list.collectAsStateWithLifecycle().value
    Log.d("RoutineSelection", "list: $list")
    val colors = List(list.size) { index ->
        val colorResources = listOf(
            R.color.happyblue,
            R.color.happyyellow,
            R.color.happygreen,
            R.color.happypink,
            R.color.happyorange,
            R.color.happypurple
        )
        colorResource(id = colorResources[index % colorResources.size])
    }
    val addRoutine = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(start = 10.dp, top = 20.dp, end = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(R.string.your_routines),
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            )
            IconButton(
                onClick = {
                    //onNavigateToRoutineAdd()
                    addRoutine.value = true
                },
                modifier = Modifier.size(48.dp) // Adjust the size as needed
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Routine",
                    modifier = Modifier.size(30.dp), // Adjust the size as needed
                    tint = Color.Black // Adjust the color as needed
                )
            }
        }

        if (list.isEmpty()) {
            EmptyRoutineList()
        } else {
            RoutineList(list = list, colors = colors, onStart = onStart, onNavigateToExerciseAdd = onNavigateToExerciseAdd)
        }

        if (addRoutine.value) {
            AddRoutineDialog(
                onDismissRequest = { addRoutine.value = false },
                onConfirmation = { name ->
                    if (name != "") {

                        viewModel.insert(Routine(
                            id = viewModel.highestId() + 1,
                            name = name,
                            exercises = emptyList(),
                            description = ""
                        ))
                    }
                    addRoutine.value = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyRoutineList() {
    EmptyRoutineList()
}

