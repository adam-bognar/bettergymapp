package project.bettergymapp.ui.screen.routines

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.repository.Routine.MockRoutineRepository
import project.bettergymapp.data.repository.Routine.RoutineViewModel
import project.bettergymapp.ui.screen.exercise.ExerciseCardWithDelete

@Composable
fun RoutineEditScreen(
    title: String = "Edit Routine",
    routine: Routine,
    onNavigateBack: () -> Unit = {},
    onNavigateToExerciseScreen: (routine: Routine) -> Unit = {},
    navController: NavController,
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory),
) {
    var routineName by remember { mutableStateOf(routine.name) }
    Log.d("pacos", "Routine name: $routineName")
    var exercises by remember { mutableStateOf(routine.exercises) }


    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    savedStateHandle?.getLiveData<String>("updatedRoutine")
        ?.observeAsState()?.value?.let { routineJson ->
                val updatedRoutine = Gson().fromJson(routineJson, Routine::class.java)
                routineName = updatedRoutine.name
                exercises = updatedRoutine.exercises
            savedStateHandle.getLiveData<String>("updatedRoutine").value = null
            savedStateHandle.remove<String>("updatedRoutine")

        }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ) {
        Column {

            RoutineEditHeader(
                title = title,
                onNavigateBack = onNavigateBack
            )
            HorizontalDivider(thickness = 3.dp, color = colorResource(R.color.routine_card))


            OutlinedTextField(
                value = routineName,
                onValueChange = {
                    routineName = it
                },
                label = { Text("Routine name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    colorResource(id = R.color.text_color),
                    unfocusedTextColor = colorResource(id = R.color.text_color),
                    focusedBorderColor = colorResource(id = R.color.button_color),
                    unfocusedBorderColor = colorResource(id = R.color.text_color),
                    focusedLabelColor = colorResource(id = R.color.text_color),
                    unfocusedLabelColor = colorResource(id = R.color.text_color),
                    cursorColor = colorResource(id = R.color.text_color),
                )
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

                item {
                    TextButton(
                        onClick = {
                            onNavigateToExerciseScreen(
                                routine.copy(
                                    name = routineName,
                                    exercises = exercises
                                )
                            )

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                    ) {
                        Text(
                            text = "Add new exercise",
                            color = colorResource(id = R.color.button_color),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = {
                viewModel.upsert(routine.copy(name = routineName, exercises = exercises))
                onNavigateBack()
            },
            shape = RoundedCornerShape(100),
            containerColor = colorResource(R.color.button_color),
        ) {
            Icon(
                Icons.Default.Check,
                "Floating action button.",
                modifier = Modifier.size(30.dp),
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun RoutineEditScreenPreview() {
    val sampleRoutine = Routine(
        name = "Sample Routine",
        description = "This is a sample routine",
        exercises = listOf(
            Exercise(name = "Push Up"),
            Exercise(name = "Squat")
        )
    )

    val mockViewModel = RoutineViewModel(repository = MockRoutineRepository()).apply {
        // Initialize the ViewModel with any necessary data
    }

    RoutineEditScreen(
        routine = sampleRoutine,
        onNavigateBack = {},
        onNavigateToExerciseScreen = {},
        navController = rememberNavController(),
        viewModel = mockViewModel,
    )
}