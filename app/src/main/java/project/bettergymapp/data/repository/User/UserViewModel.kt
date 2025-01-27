package project.bettergymapp.data.repository.User

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import project.bettergymapp.MainActivity
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.Session

class UserViewModel(
    private val repository: IUserRepository
):ViewModel() {
     fun getExercises() {
       viewModelScope.launch {
           repository.getExercises()
       }
    }
     fun getRoutines() {
        viewModelScope.launch {
            repository.getRoutines()
        }
    }
     fun getSessions() {
        viewModelScope.launch {
            repository.getSessions()
        }
    }
     fun setExercises(exercises: List<Exercise>) {
        viewModelScope.launch {
            repository.setExercises(exercises)
        }
    }
     fun setRoutines(routines: List<Routine>) {
        viewModelScope.launch {
            repository.setRoutines(routines)
        }
    }
     fun setSessions(sessions: List<Session>) {
        viewModelScope.launch {
            repository.setSessions(sessions)
        }
    }
     fun save() {
        viewModelScope.launch {
            repository.save()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserViewModel(repository = MainActivity.userRepository)
            }
        }
    }


}