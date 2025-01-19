package project.bettergymapp.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import project.bettergymapp.EXERCISE_ADD_SCREEN
import project.bettergymapp.HOME_SCREEN
import project.bettergymapp.ROUTINE_ADD_SCREEN
import project.bettergymapp.SIGN_IN_SCREEN
import project.bettergymapp.SIGN_UP_SCREEN
import project.bettergymapp.SPLASH_SCREEN
import project.bettergymapp.WORKOUT_SCREEN
import project.bettergymapp.data.Routine
import project.bettergymapp.data.viewmodel.RoutineViewModel
import project.bettergymapp.ui.screen.sign_in.LoginPage
import project.bettergymapp.ui.screen.sign_up.RegisterPage
import project.bettergymapp.ui.screen.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()

){
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ){
        composable(HOME_SCREEN) {
            MainScreen(
                onNavigateToWorkout = { routine ->
                    val routineJson = Gson().toJson(routine)
                    navController.navigate("$WORKOUT_SCREEN/$routineJson")
                },
                onNavigateToRoutineAdd = {
                    navController.navigate(ROUTINE_ADD_SCREEN)
                },
                onNavigateToExerciseAdd = {
                        routine ->
                    val routineJson = Gson().toJson(routine)
                    navController.navigate("$EXERCISE_ADD_SCREEN/$routineJson")
                },
                openAndPopUp = { destination ->
                    navController.navigate(destination)
                }
            )
        }

        composable(
            route = "$WORKOUT_SCREEN/{routine}",
            arguments = listOf(navArgument("routine") { type = NavType.StringType })
        ) { backStackEntry ->
            val routineJson = backStackEntry.arguments?.getString("routine")
            val routine = Gson().fromJson(routineJson, Routine::class.java)
            WorkoutPage(routine,
                onNavigateBack = {
                    navController.navigate(HOME_SCREEN)
                }
            )
        }

        composable(
            ROUTINE_ADD_SCREEN
        ) {
            val viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory)

            RoutineEditScreen(
                onNavigateBack = {
                    navController.navigate(HOME_SCREEN)
                },
                onNavigateToExerciseScreen = { routine ->
                    val routineJson2 = Gson().toJson(routine)
                    navController.navigate("$EXERCISE_ADD_SCREEN/$routineJson2")
                },
                routine = Routine(
                    id = viewModel.highestId() + 1,
                    name = "",
                    description = ""
                ),
                navController = navController
            )
        }

        composable("$EXERCISE_ADD_SCREEN/{routine}",
            arguments = listOf(navArgument("routine") { type = NavType.StringType })
        ) { backStackEntry ->
            val routineJson = backStackEntry.arguments?.getString("routine")
            val routine = Gson().fromJson(routineJson, Routine::class.java)
            val viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory)

            ExerciseScreen(
                routine = routine,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSelected = { update ->
                    val previousBackStackEntry = navController.previousBackStackEntry
                    val previousRoute = previousBackStackEntry?.destination?.route

                    if (previousRoute != null) {
                        // Do something based on the previous route
                        when (previousRoute) {
                            HOME_SCREEN -> {
                                // Handle the case when the previous screen was "home"
                                viewModel.upsert(update)
                            }
                            ROUTINE_ADD_SCREEN -> {
                                // Handle the case when the previous screen was "routine add"
                                val updateRoutineJson = Gson().toJson(update)
                                previousBackStackEntry.savedStateHandle.set("updatedRoutine", updateRoutineJson)
                                Log.d("NavGraph", "Updated routine: ${update.exercises.joinToString { it.name }}")
                            }
                            // Add more cases as needed
                        }
                    }
                }
            )
        }

        composable(SIGN_IN_SCREEN) {
            LoginPage(
                onLoginClick = { destination ->
                    navController.navigate(destination)
                },
            )
        }

        composable(SIGN_UP_SCREEN) {
            RegisterPage(
                onRegisterClick = { destination ->
                    navController.navigate(destination)
                },
            )
        }

        composable(SPLASH_SCREEN) {
            SplashScreen(
                openAndPopUp = { destination ->
                    navController.navigate(destination)
                }
            )
        }
    }
}