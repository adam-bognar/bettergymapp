package project.bettergymapp.data.repository.Exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import project.bettergymapp.MainActivity
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.repository.IExerciseRepository

class ExerciseViewModel(
    private val repository: IExerciseRepository
) : ViewModel() {
    private val _list = MutableStateFlow<List<Exercise>>(listOf())
    val list = _list.asStateFlow()

    init {
        getAllExercises()
    }

    private fun getAllExercises() {
        viewModelScope.launch {
            repository.getAllExercises().collectLatest {
                _list.tryEmit(it)
            }
        }
    }

    fun exerciseCount(): Int {
        return _list.value.size
    }

    fun getExercise(index: Int): Exercise {
        for (exercise in _list.value) {
            if (exercise.id == index) {
                return exercise
            }
        }
        return Exercise()
    }

    fun exerciseExists(id: Int): Boolean {
        for (exercise in _list.value) {
            if (exercise.id == id) {
                return true
            }
        }
        return false
    }

    fun upsert(item: Exercise) {
        viewModelScope.launch {
            try {
                repository.upsert(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun delete(item: Exercise) {
        viewModelScope.launch {
            try {
                repository.delete(item)
            } catch (e: Exception) {
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ExerciseViewModel(repository = MainActivity.exerciseRepository)
            }
        }
    }

//    fun highestId(): Int {
//        var highestId = 0
//        viewModelScope.launch {
//            try {
//                highestId = repository.highestId()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//        return highestId
//    }

}