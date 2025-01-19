package project.bettergymapp.data.repository.Session

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
import project.bettergymapp.data.Session
import project.bettergymapp.data.repository.ISessionRepository

class SessionViewModel(
    private val repository: ISessionRepository
) : ViewModel() {
    private val _list = MutableStateFlow<List<Session>>(listOf())
    val list = _list.asStateFlow()


    init {
        getAllSessions()
    }

    private fun getAllSessions() {
        viewModelScope.launch {
            repository.getAllSessions().collectLatest {
                _list.tryEmit(it)
            }
        }
    }

    fun sessionCount(): Int {
        return _list.value.size
    }

    fun getSession(index: Int): Session {
        return _list.value[index]
    }

    fun upsert(item: Session) {
        viewModelScope.launch {
            try {
                repository.upsert(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun delete(item: Session) {
        viewModelScope.launch {
            try {
                repository.delete(item)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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




    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SessionViewModel(repository = MainActivity.sessionRepository)
            }
        }
    }


}