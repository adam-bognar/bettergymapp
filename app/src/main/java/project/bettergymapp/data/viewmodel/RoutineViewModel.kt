package project.bettergymapp.data.viewmodel

import android.util.Log
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
import project.bettergymapp.data.Routine
import project.bettergymapp.data.repository.IRoutineRepository

class RoutineViewModel(
    private val repository: IRoutineRepository
) : ViewModel() {
    private val _list = MutableStateFlow<List<Routine>>(listOf())
    val list = _list.asStateFlow()


    init {
        getAllRoutines()
        Log.d("RoutineViewModel", "list: $_list")
    }

    private fun getAllRoutines() {


        viewModelScope.launch {
            repository.getAllRoutines().collectLatest {
                _list.tryEmit(it)
            }
        }
    }

    fun routineCount(): Int {
        return _list.value.size
    }

    fun getRoutine(index: Int): Routine {
        return _list.value[index]
    }

    fun highestId(): Int {
        var highestId = 0
        viewModelScope.launch {
            try {
                highestId = repository.highestId()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return highestId
    }

    fun insert(item: Routine) {
        viewModelScope.launch {
            try {
                repository.insert(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun update(item: Routine) {
        viewModelScope.launch {
            try {
                repository.update(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun delete(item: Routine) {
        viewModelScope.launch {
            try {
                repository.delete(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            try {
                repository.save()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RoutineViewModel(repository = MainActivity.routineRepository)
            }
        }
    }
}