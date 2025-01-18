package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Routine

interface IRoutineRepository {
    fun getAllRoutines(): Flow<List<Routine>>
    suspend fun insert(routine: Routine)
    suspend fun delete(routine: Routine)
    suspend fun update(routine: Routine)
    suspend fun highestId(): Int
    suspend fun deleteAll()
    suspend fun save()
}