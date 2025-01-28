package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R
import project.bettergymapp.data.Routine
import project.bettergymapp.data.repository.Routine.RoutineViewModel
import project.bettergymapp.ui.screen.routines.RoutineList

@Composable
fun MainScreen(
    onNavigateToWorkout: (routine: Routine) -> Unit = {},
    onNavigateToRoutineAdd: (Routine) -> Unit = {},
    onNavigateToExerciseAdd: (routine: Routine) -> Unit = {},
    openAndPopUp: (String) -> Unit = {},
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory),

    ){
    Column(modifier = Modifier.fillMaxSize()
        .background(colorResource(R.color.background))
    )
    {
//        TopAppBar(
//            "username",
//            openAndPopUp = openAndPopUp
//        )


        val list by viewModel.list.collectAsStateWithLifecycle()

        Column(modifier = Modifier.padding(start = 10.dp, top = 20.dp, end = 10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(R.string.select_workout),
                    color = colorResource(R.color.text_color),
                    modifier = Modifier.padding(top = 10.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                )
            }

            if (list.isEmpty()) {
                EmptyRoutineList()
            } else {
                RoutineList(
                    list = list,
                    onStart = onNavigateToWorkout,
                    onNavigateToRoutineAdd = onNavigateToRoutineAdd,
                    onRoutineDelete = { routine ->
                        viewModel.delete(routine)
                    }
                )
            }

        }
    }
}