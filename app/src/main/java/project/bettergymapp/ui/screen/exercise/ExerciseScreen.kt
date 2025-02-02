package project.bettergymapp.ui.screen.exercise

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.repository.Exercise.ExerciseViewModel
import project.bettergymapp.data.repository.Exercise.MockExerciseRepository
import project.bettergymapp.data.retrofit.ExerciseFromApi
import project.bettergymapp.data.retrofit.RetrofitClient
import project.bettergymapp.ui.screen.routines.RoutineEditHeader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ExerciseScreen(
    routine: Routine,
    onNavigateBack: () -> Unit = {},
    onSelected: (Routine) -> Unit,
    viewModel: ExerciseViewModel = viewModel(factory = ExerciseViewModel.Factory)
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var exercises by remember { mutableStateOf(listOf<ExerciseFromApi>()) }
    val muscles = listOf("Chest", "Back", "Legs", "Shoulders", "Biceps", "Triceps", "Abs")
    var filterOpen by remember { mutableStateOf(false) }
    var selectedButton by remember { mutableStateOf<String?>(null) }



    LaunchedEffect(searchQuery.text, selectedButton) {
        fetch(searchQuery.text, selectedButton) { fetchedExercises ->
            exercises = fetchedExercises.sortedBy { it.name }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
    ) {
        RoutineEditHeader(
            onNavigateBack = {
                onNavigateBack()
            }
        )
        HorizontalDivider(thickness = 3.dp, color = colorResource(R.color.routine_card))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp),
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
            TextButton(
                onClick = {
                    filterOpen = !filterOpen
                },
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),

                ) {
                Text("Filter",
                    color = colorResource(id = R.color.button_color),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (filterOpen) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(4),
            ) {
                items(muscles.size) { muscle ->
                    MusclesButton(
                        muscles[muscle],
                        isSelected = selectedButton == muscles[muscle],
                        onClick = {
                            selectedButton =
                                if (selectedButton == muscles[muscle]) null else muscles[muscle]
                            Log.d("ExerciseScreen", "Selected button: $selectedButton")
                        }
                    )
                }
            }
        }
        LazyColumn { // This is a vertically scrolling list.
            items(exercises.size) { exercise ->
                ExerciseCard(exercises[exercise].name,
                    onClick = {

                        Log.d("ExerciseScreen", "clicked")
                        val updatedExercises = routine.exercises.toMutableList()
                        val updatedExercise: Exercise = if(viewModel.exerciseExists(exercises[exercise].id)){
                            viewModel.getExercise(exercises[exercise].id)

                        }else{
                            Exercise(
                                id = exercises[exercise].id,
                                name = exercises[exercise].name
                            )
                        }
                        Log.d("ExerciseScreen", "Updated exercise: $updatedExercise")
                        updatedExercises.add(updatedExercise)
                        val updatedRoutine = routine.copy(exercises = updatedExercises)

                        onSelected(updatedRoutine)

                        onNavigateBack()

                    }
                )
            }
        }
    }
}


private fun fetch(name: String, muscleGroup: String?, onFetched: (List<ExerciseFromApi>) -> Unit) {
    val call = if (name.isNotEmpty() || muscleGroup != null) {
        RetrofitClient.instance.getExercisesByFilters(name.takeIf { it.isNotEmpty() }, muscleGroup)
    } else {
        RetrofitClient.instance.getExercises()
    }
    call.enqueue(object : Callback<List<ExerciseFromApi>> {
        override fun onResponse(
            call: Call<List<ExerciseFromApi>>,
            response: Response<List<ExerciseFromApi>>
        ) {
            if (response.isSuccessful) {
                val products = response.body()
                products?.let {
                    onFetched(it)
                }
            }
        }

        override fun onFailure(call: Call<List<ExerciseFromApi>>, t: Throwable) {

        }
    })
}

@Preview(showBackground = true)
@Composable
fun ExerciseScreenPreview() {
    val sampleRoutine = Routine(
        id = 1,
        name = "Routine 1",
        description = "",
        exercises = emptyList()
    )
    val mockViewModel = ExerciseViewModel(repository = MockExerciseRepository()).apply {
        // Initialize the ViewModel with any necessary data
    }
    ExerciseScreen(
        routine = sampleRoutine,
        onNavigateBack = {},
        onSelected = {},
        viewModel = mockViewModel
    )
}