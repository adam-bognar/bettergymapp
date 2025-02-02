package project.bettergymapp.ui.screen.routines

import RoutineCard
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R
import project.bettergymapp.data.Routine
import project.bettergymapp.data.repository.Routine.RoutineViewModel

@Composable
fun RoutineList(
    list: List<Routine>,
    onStart: (Routine) -> Unit = {},
    onNavigateToRoutineAdd: (Routine) -> Unit,
    onRoutineDelete: (Routine) -> Unit = {},
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory)
) {
    var showOptions by remember { mutableStateOf(false) }
    var selectedRoutine: Routine by remember { mutableStateOf(Routine()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {


        // Top part: HorizontalPager & Exercises
        LazyColumn {
            items(list.size) { index ->
                RoutineCard(
                    routine = list[index], onStartClick = onStart,
                    onOptionsClick = {
                        selectedRoutine = list[index]
                        showOptions = !showOptions
                        Log.d("RoutineList", "onOptionsClick: $selectedRoutine")
                        Log.d("RoutineList", "onOptionsClick: $showOptions")

                    },
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = {
                onNavigateToRoutineAdd(
                    Routine(
                        id = viewModel.highestId()+1,
                        name = "",
                        description = "",
                        exercises = emptyList()
                    )
                )
            },
            shape = RoundedCornerShape(100),
            containerColor = colorResource(R.color.button_color),
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

        AnimatedVisibility(
            visible = showOptions,
            enter = slideInVertically(
                initialOffsetY = { it } // Start from below the screen
            ),
            exit = slideOutVertically(
                targetOffsetY = { it } // Exit to below the screen
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            RoutineOptionsPopUp(
                routine = selectedRoutine,
                modifier = Modifier.align(Alignment.BottomCenter),
                onClose = {
                    showOptions = false
                },
                onRoutineDelete = onRoutineDelete,
                onRoutineEdit = {
                    onNavigateToRoutineAdd(it)
                }
            )


        }
    }

}
@Preview(showBackground = true)
@Composable
fun RoutineListPreview() {
    val routine = Routine(name = "Routine 1", description = "")
    RoutineList(list = listOf(routine), onStart = {}, onNavigateToRoutineAdd = {})
}