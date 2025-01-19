package project.bettergymapp.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import project.bettergymapp.R
import project.bettergymapp.data.Routine
import project.bettergymapp.data.viewmodel.RoutineViewModel

@Composable
fun RoutineEditScreen(
    routine: Routine,
    onNavigateBack: () -> Unit = {},
    onNavigateToExerciseScreen: (routine: Routine) -> Unit = {},
    navController: NavController,
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory)
) {
    var routineName by remember { mutableStateOf(routine.name) }
    var exercises by remember { mutableStateOf(routine.exercises) }

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    savedStateHandle?.getLiveData<String>("updatedRoutine")?.observeAsState()?.value?.let { routineJson ->
        val updatedRoutine = Gson().fromJson(routineJson, Routine::class.java)
        routineName = updatedRoutine.name
        exercises = updatedRoutine.exercises
        savedStateHandle.remove<String>("updatedRoutine")
        Log.d("RoutineEditScreen", "haló")
    }

    LaunchedEffect(exercises) {
        Log.d("RoutineEditScreen", "Exercises: ${exercises.joinToString { it.name }}")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()) {

        RoutineEditHeader(
            onNavigateBack = onNavigateBack,
            onSaved = {
                viewModel.upsert(routine.copy(name = routineName, exercises = exercises))
            }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = routineName,
            onValueChange = { newValue ->
                routineName = newValue
            },
            label = { Text("Routine name") },
            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = colorResource(id = R.color.top_app_bar))
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(exercises.size) { index ->
                ExerciseCardWithDelete(
                    exercises[index].name,
                    onDelete = {
                        exercises.toMutableList().removeAt(index)
                    }
                )
            }

            item{
                TextButton(
                    onClick = {
                        onNavigateToExerciseScreen(routine.copy(name = routineName, exercises = exercises))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 10.dp),
                ) {
                    Text(
                        text = "Add new exercise",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.happyblue)
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


    }
}

@Composable
fun RoutineEditHeader(
    onNavigateBack: () -> Unit,
    onSaved: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.top_app_bar))
            .drawBottomBorder(1.dp, Color.Gray),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = { onNavigateBack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back"
            )
        }

        Text(
            text = "Edit Routine",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )

        TextButton(
            onClick = {
                onSaved()
                onNavigateBack()
            }
        ) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutineEditScreenPreview() {
    RoutineEditScreen(
        routine = Routine(name = "Routine 1", description = "", exercises = listOf()),
        onNavigateBack = {},
        onNavigateToExerciseScreen = {},
        navController = rememberNavController()
    )
}