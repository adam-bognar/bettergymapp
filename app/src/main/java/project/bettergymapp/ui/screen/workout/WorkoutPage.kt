package project.bettergymapp.ui.screen.workout

import Timer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.Session
import project.bettergymapp.data.repository.Exercise.ExerciseViewModel
import project.bettergymapp.data.repository.Exercise.MockExerciseRepository
import project.bettergymapp.data.repository.Routine.MockRoutineRepository
import project.bettergymapp.data.repository.Routine.RoutineViewModel
import project.bettergymapp.data.repository.Session.MockSessionRepository
import project.bettergymapp.data.repository.Session.SessionViewModel

@Composable
fun WorkoutPage(
    routine: Routine,
    onNavigateBack: () -> Unit,
    routineViewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory),
    exerciseViewModel: ExerciseViewModel = viewModel(factory = ExerciseViewModel.Factory),
    sessionViewModel: SessionViewModel = viewModel(factory = SessionViewModel.Factory),
    onNavigateToExerciseAdd: (routine: Routine) -> Unit
) {
    var exercises by remember { mutableStateOf(routineViewModel.getRoutine(routine.id).exercises) }
    Log.d("WorkoutPage", "exercises: $exercises")
    val showTimer = remember { mutableStateOf(false) }
    var showTimerSettings by remember { mutableStateOf(false) }
    var isSwitched by remember { mutableStateOf(true) }
    var timerTime by remember { mutableIntStateOf(120) }


    Box(
        modifier = Modifier.fillMaxSize() // This allows stacking elements.
    ) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize().background(colorResource(R.color.background))
        ) {
            WorkoutHeader(
                name = routine.name,
                onFinish = {
                    routineViewModel.upsert(routine.copy(exercises = exercises))
                    exercisesToFireBase(exercises, exerciseViewModel)
                    sessionToFirebase(routine.name,exercises, sessionViewModel)
                    onNavigateBack()
                },
                onTimerClick = { showTimerSettings = true },
                onBack = { onNavigateBack() }
            )
            HorizontalDivider(thickness = 3.dp, color = colorResource(R.color.routine_card))


            LazyColumn(
                modifier = Modifier.weight(1f) // Allows LazyColumn to take up remaining space.
            ) {
                items(exercises.size) { index ->
                    WorkoutItem(
                        exercise = exercises[index],
                        onUpdate = { updatedExercise ->
                            exercises = exercises.toMutableList().apply {
                                set(index, updatedExercise)
                            }
                        },
                        onDoneClick = { showTimer.value = true }
                    )
                }

                item {
                    TextButton(
                        onClick = {
                            onNavigateToExerciseAdd(routine.copy(exercises = exercises))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 10.dp),
                    ) {
                        Text(
                            text = "Add new exercise",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                color = colorResource(id = R.color.button_color)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

        }

        if (!isSwitched) {
            showTimer.value = false
        }

        // Timer at the bottom
        if (showTimer.value && isSwitched) {
            Timer(
                initialTime = timerTime,
                onDismiss = { showTimer.value = false },
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Aligns the timer to the bottom.
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        if (showTimerSettings) {
            TimerDialog(
                onDismissRequest = { showTimerSettings = false },
                modifier = Modifier
                    .align(Alignment.Center),
                onSwitchChange = { isChecked ->
                    isSwitched = isChecked
                },
                onTimeChange = { time ->
                    timerTime = time
                },
                initialTime = timerTime,
                switchState = isSwitched
            )
        }
    }

}

fun sessionToFirebase(name: String ,exercises: List<Exercise>, sessionViewModel: SessionViewModel) {
    val session = Session(
        id = sessionViewModel.highestId() + 1,
        name = name,
        date = Timestamp.now(),
        duration = 0,
        log = exercises
    )
    Log.d("sessionToFirebase", "Session: $session")
    sessionViewModel.upsert(session)
}

fun exercisesToFireBase(exercises: List<Exercise>, exerciseViewModel: ExerciseViewModel) {
    for (exercise in exercises) {
        exerciseViewModel.upsert(exercise)
        Log.d("exercisesToFireBase", "Exercise: $exercise")
    }
}


@Preview(showBackground = true)
@Composable
fun WorkoutPagePreview() {
    val exercises = listOf(
        Exercise(name = "Exercise 1"),
        Exercise(name = "Exercise 2"),
        Exercise(name = "Exercise 3"),
    )

    val sampleRoutine = Routine(name = "Routine 1", description = "", exercises = exercises)

    val mockRoutineViewModel = RoutineViewModel(MockRoutineRepository()).apply {
    }
    val mockExerciseViewModel = ExerciseViewModel(MockExerciseRepository()).apply {
    }
    val mockSessionViewModel = SessionViewModel(MockSessionRepository()).apply {
    }

    WorkoutPage(
        routine = sampleRoutine,
        onNavigateBack = {},
        routineViewModel = mockRoutineViewModel,
        exerciseViewModel = mockExerciseViewModel,
        sessionViewModel = mockSessionViewModel,
        onNavigateToExerciseAdd = {}
    )
}