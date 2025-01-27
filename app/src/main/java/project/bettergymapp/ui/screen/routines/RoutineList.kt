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
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine

@Composable
fun RoutineList(
    list: List<Routine>,
    onStart: (Routine) -> Unit = {},
    onNavigateToRoutineAdd: () -> Unit,
    onRoutineDelete: (Routine) -> Unit = {},
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
                onNavigateToRoutineAdd()
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
                    //TODO
                }
            )


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
    RoutineList(list = sampleRoutines, onStart = {}, onNavigateToRoutineAdd = {})
}
