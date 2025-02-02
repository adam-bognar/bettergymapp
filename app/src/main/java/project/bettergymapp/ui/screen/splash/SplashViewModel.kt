package project.bettergymapp.ui.screen.splash

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import project.bettergymapp.HOME_SCREEN
import project.bettergymapp.MainActivity
import project.bettergymapp.SIGN_IN_SCREEN
import project.bettergymapp.data.repository.Exercise.ExerciseRepositoryImpl
import project.bettergymapp.data.repository.Routine.RoutinesRepositoryImpl
import project.bettergymapp.data.repository.Routine.SessionRepositoryImpl
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.viewmodel.AppViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
  private val accountService: AccountService
) : AppViewModel() {

  suspend fun onAppStart(openAndPopUp: (String) -> Unit) {
    if (accountService.hasUser()){

        val db = Firebase.firestore

            MainActivity.routineRepository = RoutinesRepositoryImpl(db, accountService.currentUserId)
            MainActivity.exerciseRepository = ExerciseRepositoryImpl(db, accountService.currentUserId)
            MainActivity.sessionRepository = SessionRepositoryImpl(db, accountService.currentUserId)

        openAndPopUp(HOME_SCREEN)
    }
    else openAndPopUp(SIGN_IN_SCREEN)
  }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            SplashViewModel(MainActivity.accountService)
        }
        }
    }
}