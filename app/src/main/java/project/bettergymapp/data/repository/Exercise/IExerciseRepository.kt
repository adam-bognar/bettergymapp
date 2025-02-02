package project.bettergymapp.data.repository.Exercise

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Exercise

interface IExerciseRepository {
    fun getAllExercises(): Flow<List<Exercise>>
    suspend fun upsert(exercise: Exercise)
    suspend fun delete(exercise: Exercise)


}