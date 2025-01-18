package project.bettergymapp.data.repository.User

import kotlinx.coroutines.flow.MutableStateFlow
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.Session

interface IUserRepository {
    suspend fun getExercises(): MutableStateFlow<List<Exercise>>
    suspend fun setExercises(exercises: List<Exercise>)
    suspend fun getRoutines(): MutableStateFlow<List<Routine>>
    suspend fun setRoutines(routines: List<Routine>)
    suspend fun getSessions(): MutableStateFlow<List<Session>>
    suspend fun setSessions(sessions: List<Session>)
    suspend fun save()
}