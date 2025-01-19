package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import project.bettergymapp.R
import project.bettergymapp.data.Routine

@Composable
fun MainScreen(
    onNavigateToWorkout: (routine: Routine) -> Unit = {},
    onNavigateToRoutineAdd: () -> Unit = {},
    onNavigateToExerciseAdd: (routine: Routine) -> Unit = {},
    openAndPopUp: (String) -> Unit = {},
){




    Column(modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.beige))
    )
    {
        TopAppBar(
            "username",
            openAndPopUp = openAndPopUp
        )
        RoutineSelection(onStart = onNavigateToWorkout, onNavigateToRoutineAdd = onNavigateToRoutineAdd, onNavigateToExerciseAdd = onNavigateToExerciseAdd)
    }
}